package jobexe;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import cache.CentralContainer;
import common.Pair;
import dag.JobDag;
import dag.TaskNode;
import executorengine.JobExecutorEngine;
import jobexe.context.JobContext;
import jobexe.result.JobResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tasks.SingleTaskService;
import tasks.output.TaskOutput;

import static util.LogUtils.JOB_EXE_LOGGER;

/**
 * this class should have following components:
 * 1. dag, as the job-task relation
 * 2. exe engine
 * 3. exe status (what task should be processed next)  *not need in constructor
 * 4. context? (when the task is instantiated?)  * YES, in service level,
 *                          dont process request, request -> context should be done earlier
 *                          so that one request can be routed to different job service
 * 5. JOB ID
 * @author xiaohan
 * @since 2020/2/2 9:49 AM
 */
@Service
public class JobExeServiceImpl extends AbstractJobExeService<String> {

    private Logger logger = LoggerFactory.getLogger(JOB_EXE_LOGGER);

    @Resource
    private CentralContainer container;

    @Resource
    private JobExecutorEngine threadPoolJobExecutorEngine;

    private JobDag jobDag;


    public JobExeServiceImpl(String jobId, JobDag jobDag) {
        super(jobId);
        this.jobDag = jobDag;
    }

    @Override
    JobResult innerJobRun(JobContext context) {
        JobStatus jobStatus = new JobStatus(jobDag);
        JobContext jobContext = new JobContext(new Date());
        TaskOutput lastTaskOut = null;
        while(!jobStatus.isFinish()){

            //1.start exe 1 task
            kickOffOneWaitingTask(jobStatus, jobContext);

            //2.check if any done task exist
            // (with the principle that not blocking nodes in waiting list to be executed)
            ArrayList<TaskNode> completedNodes = new ArrayList<>();
            lastTaskOut = checkAnyDoneTask(jobStatus, lastTaskOut, completedNodes);

            //3.add to complete list and prepare its son nodes
            proceedTheirSonNodes(jobStatus, completedNodes);
        }

        jobContext.setEndTime(new Date());

        //set the last job's result into this
        JobResult jobResult = new JobResult();
        jobResult.setLastJobOutput(lastTaskOut);
        return jobResult;
    }

    private void proceedTheirSonNodes(JobStatus jobStatus, ArrayList<TaskNode> completedNodes) {
        //prepare its son nodes when it's done
        for (TaskNode completedNode : completedNodes) {
            Set<TaskNode> sonNodes = completedNode.getSonNodes();
            jobStatus.addNodeToWaitingQueue(new ArrayList<>(sonNodes));
        }
    }

    private TaskOutput checkAnyDoneTask(JobStatus jobStatus, TaskOutput lastTaskOut, ArrayList<TaskNode> completedNodes) {
        Iterator<Pair<TaskNode, Future<TaskOutput>>> iterator = jobStatus.getProcessingTasks().iterator();
        while (iterator.hasNext()){
            Pair<TaskNode, Future<TaskOutput>> pair = iterator.next();
            boolean done = pair.getRight().isDone();
            if(done){
                try {
                    //todo doubting.....
                    lastTaskOut = pair.getRight().get();
                    completedNodes.add(pair.getLeft());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        return lastTaskOut;
    }

    private void kickOffOneWaitingTask(JobStatus jobStatus, JobContext jobContext) {
        //get task instance
        TaskNode taskToRun = jobStatus.getNextWaitingTask();
        SingleTaskService task = container.getTask(taskToRun.getTaskId());

        //delegate to execute engine
        Future<TaskOutput> executeFuture = threadPoolJobExecutorEngine.execute(() -> {
            TaskOutput taskOutput = task.runTask(jobContext);
            return taskOutput;
        });

        //add to the processing list
        jobStatus.addProcessingNode(taskToRun, executeFuture);
    }
}

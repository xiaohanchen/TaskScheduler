package jobexe;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import cache.CentralContainer;
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

    private JobDag jobDag;

    private JobExecutorEngine engine;


    public JobExeServiceImpl(String jobId, JobDag jobDag, JobExecutorEngine engine) {
        super(jobId);
        this.jobDag = jobDag;
        this.engine = engine;
    }

    @Override
    JobResult innerJobRun(JobContext context) {
        JobStatus jobStatus = new JobStatus(jobDag);
        JobContext jobContext = new JobContext(new Date());
        TaskOutput lastTaskOut = null;
        while(!jobStatus.isFinish()){

            TaskNode taskToRun = jobStatus.getNextWaitingTask();

            SingleTaskService task = container.getTask(taskToRun.getTaskId());
            TaskOutput taskOutput = task.runTask(jobContext);
            lastTaskOut = taskOutput;

            Set<TaskNode> sonNodes = taskToRun.getSonNodes();
            jobStatus.addNodeToWaitingQueue(new ArrayList<>(sonNodes));

        }

        jobContext.setEndTime(new Date());

        //set the last job's result into this
        JobResult jobResult = new JobResult();
        jobResult.setLastJobOutput(lastTaskOut);
        return jobResult;
    }
}

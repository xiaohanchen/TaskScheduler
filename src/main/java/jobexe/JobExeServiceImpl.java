package jobexe;

import dag.JobDag;
import executorengine.JobExecutorEngine;
import jobexe.context.JobContext;
import jobexe.result.JobResult;

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
public class JobExeServiceImpl extends AbstractJobExeService<String> {


    private JobDag jobDag;

    private JobExecutorEngine engine;




    @Override
    JobResult innerJobRun(JobContext context) {
        JobStatus jobStatus = new JobStatus();

        return null;
    }
}

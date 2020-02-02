package jobexe;

import jobexe.context.JobContext;
import jobexe.result.JobExecutionResult;

/**
 * @author xiaohan
 * @since 2020/2/2 9:46 AM
 */
public interface JobExeService<R> {

    /**
     * inner logic of how a job should processed
     * @return
     */
    JobExecutionResult runJob(JobContext context);
}

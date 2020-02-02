package executorengine;

import jobexe.JobExeService;

/**
 * execution API, can be single thread/multi thread/ or async
 *
 * input is the job
 * @author xiaohan
 * @since 2020/1/31 5:51 PM
 */
public interface JobExecutorEngine {

    /**
     * should call jobExeService's inner process logic
     * @param jobExeService
     * @return
     */
    boolean execute(JobExeService jobExeService);
}

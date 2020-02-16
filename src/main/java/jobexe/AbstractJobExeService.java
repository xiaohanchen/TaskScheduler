package jobexe;

import jobexe.context.JobContext;
import jobexe.result.JobExecutionResult;
import jobexe.result.JobResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.LogUtils.JOB_EXE_LOGGER;

/**
 * @author xiaohan
 * @since 2020/2/2 10:48 AM
 */
public abstract class AbstractJobExeService<R> implements JobExeService<R> {
    private Logger logger = LoggerFactory.getLogger(JOB_EXE_LOGGER);

    public AbstractJobExeService(String jobId) {
        this.jobId = jobId;
    }

    private String jobId;

    @Override
    public JobExecutionResult runJob(JobContext context) {

        long start = System.currentTimeMillis();
        JobExecutionResult exeRes = new JobExecutionResult();

        try {
            JobResult jobResult = innerJobRun(context);
            exeRes.setSuccess(true);
            exeRes.setResult(jobResult);
        } catch (Exception e) {
            e.printStackTrace();
            exeRes.setSuccess(false);
            exeRes.setException(e.getMessage());
        }
        long end = System.currentTimeMillis();

        long timeUsed = end - start;

        logger.info("job completed, time cost {}", timeUsed);
        return exeRes;
    }


    abstract JobResult innerJobRun(JobContext context);
}

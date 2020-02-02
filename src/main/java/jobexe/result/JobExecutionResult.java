package jobexe.result;

/**
 * result of one job request
 * @author xiaohan
 * @since 2020/2/2 9:46 AM
 */
public class JobExecutionResult {

    private boolean success;

    private String exception;

    private JobResult result;


    public boolean isSuccess() {
        return success;
    }

    public JobExecutionResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public JobResult getResult() {
        return result;
    }

    public JobExecutionResult setResult(JobResult result) {
        this.result = result;
        return this;
    }

    public String getException() {
        return exception;
    }

    public JobExecutionResult setException(String exception) {
        this.exception = exception;
        return this;
    }
}

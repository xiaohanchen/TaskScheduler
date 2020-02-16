package jobexe.result;

import tasks.output.TaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/2 9:46 AM
 */
public class JobResult {

    public TaskOutput getLastJobOutput() {
        return lastJobOutput;
    }

    public JobResult setLastJobOutput(TaskOutput lastJobOutput) {
        this.lastJobOutput = lastJobOutput;
        return this;
    }

    private TaskOutput lastJobOutput;

}

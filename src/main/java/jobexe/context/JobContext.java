package jobexe.context;

import java.util.Date;
import java.util.HashMap;

import tasks.output.TaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/2 10:35 AM
 */
public class JobContext {

    private Date startTime;

    public JobContext setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    private Date endTime;

    /**
     * taskId, TaskOutput
     */
    private HashMap<String, TaskOutput> outputMap = new HashMap();

    public JobContext(Date startTime) {
        this.startTime = startTime;
    }

    public void addTaskResult(String taskId, TaskOutput taskOutput){
        outputMap.put(taskId, taskOutput);
    }


    public TaskOutput getTaskResult(String taskId){
        return outputMap.get(taskId);
    }


}

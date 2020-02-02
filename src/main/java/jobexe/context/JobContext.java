package jobexe.context;

import java.util.Date;
import java.util.HashMap;

import tasks.TaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/2 10:35 AM
 */
public class JobContext {

    private Date startTime;

    private Date endTime;

    /**
     * taskId, TaskOutput
     */
    HashMap<String, TaskOutput> outputMap = new HashMap();



}

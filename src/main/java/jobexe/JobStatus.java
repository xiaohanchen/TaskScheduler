package jobexe;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import dag.JobDag;
import dag.TaskNode;

/**
 * @author xiaohan
 * @since 2020/2/2 9:56 AM
 */
public class JobStatus {

    BlockingQueue<TaskNode> waitingTasks;

    List<TaskNode> processingTasks;

    List<TaskNode> completedTasks;

    public JobStatus(JobDag jobDag) {
        waitingTasks = new LinkedBlockingDeque<>();
        processingTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();

        List<TaskNode> initNodes = jobDag.getInitNodes();
        waitingTasks.addAll(initNodes);
    }



}

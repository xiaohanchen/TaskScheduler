package jobexe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import dag.JobDag;
import dag.TaskNode;

/**
 * information about the toBeProcessed/Processing/Processed Nodes
 * @author xiaohan
 * @since 2020/2/2 9:56 AM
 */
public class JobStatus {

    private final int taskSize;

    private BlockingQueue<TaskNode> waitingTasks;

    //the reason to use linkedList is that you dont know how many nodes can be processed at the same time
    private LinkedList<TaskNode> processingTasks;

    //no sequence required
    private List<TaskNode> completedTasks;

    /**
     * made up the toDoNodes
     * @param jobDag
     */
    public JobStatus(JobDag jobDag) {
        taskSize = jobDag.getAllNodes().size();
        waitingTasks = new LinkedBlockingDeque<>(6);
        processingTasks = new LinkedList<>();
        completedTasks = new ArrayList<>(taskSize);

        List<TaskNode> initNodes = jobDag.getInitNodes();
        waitingTasks.addAll(initNodes);
    }

    boolean isFinish(){
        return waitingTasks.isEmpty() && processingTasks.size() == 0 && completedTasks.size() == taskSize;
    }

    TaskNode getNextWaitingTask(){
        try {
            TaskNode task = waitingTasks.poll(1, TimeUnit.MINUTES);
            return task;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void addNodeToWaitingQueue(List<TaskNode> nodes){
        waitingTasks.addAll(nodes);
    }








}

package jobexe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import common.Pair;
import dag.JobDag;
import dag.TaskNode;
import tasks.output.TaskOutput;

/**
 * information about the toBeProcessed/Processing/Processed Nodes
 * @author xiaohan
 * @since 2020/2/2 9:56 AM
 */
public class JobStatus {

    private final int taskSize;

    private BlockingQueue<TaskNode> waitingTasks;

    //the reason to use linkedList is that you dont know how many nodes can be processed at the same time
    private LinkedList<Pair<TaskNode, Future<TaskOutput>>> processingTasks;

    //no sequence required
    private List<TaskNode> completedTasks;

    /**
     * made up the toDoNodes
     * @param jobDag
     */
    public JobStatus(JobDag jobDag) {
        taskSize = jobDag.getAllNodes().size();
        waitingTasks = new LinkedBlockingDeque<>(16);
        processingTasks = new LinkedList<>();
        completedTasks = new ArrayList<>(taskSize);

        List<TaskNode> initNodes = jobDag.getInitNodes();
        waitingTasks.addAll(initNodes);
    }

    /**
     * tell if the dag is completed
     * @return
     */
    boolean isFinish(){
        return waitingTasks.isEmpty() && processingTasks.size() == 0 && completedTasks.size() == taskSize;
    }

    /**
     * get to-do task from waiting list
     * @return
     */
    TaskNode getNextWaitingTask(){
        try {
            TaskNode task = waitingTasks.poll(1, TimeUnit.MINUTES);
            return task;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * add task to be done
     * @param nodes
     */
    void addNodeToWaitingQueue(List<TaskNode> nodes){
        waitingTasks.addAll(nodes);
    }

    /**
     * add processing node
     * @param node taskNode
     * @param future future which holds task output
     */
    void addProcessingNode(TaskNode node, Future<TaskOutput> future){
        Pair pair = new Pair(node, future);
        processingTasks.add(pair);
    }



    public LinkedList<Pair<TaskNode, Future<TaskOutput>>> getProcessingTasks() {
        return processingTasks;
    }

    public List<TaskNode> getCompletedTasks() {
        return completedTasks;
    }
}

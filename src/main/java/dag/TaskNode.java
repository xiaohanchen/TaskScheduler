package dag;

import java.util.HashSet;
import java.util.Set;

/**
 * can find father nodes and son nodes via this one
 * @author xiaohan
 * @since 2020/1/23 11:29 AM
 */
public class TaskNode {

    String taskId;

    Set<TaskNode> fatherNodes = new HashSet<>();

    Set<TaskNode> sonNodes = new HashSet<>();

    public String getTaskId() {
        return taskId;
    }

    public TaskNode setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public Set<TaskNode> getFatherNodes() {
        return fatherNodes;
    }

    public TaskNode setFatherNodes(Set<TaskNode> fatherNodes) {
        this.fatherNodes = fatherNodes;
        return this;
    }

    public Set<TaskNode> getSonNodes() {
        return sonNodes;
    }

    public TaskNode setSonNodes(Set<TaskNode> sonNodes) {
        this.sonNodes = sonNodes;
        return this;
    }

    public TaskNode(String taskId) {
        this.taskId = taskId;
    }
}

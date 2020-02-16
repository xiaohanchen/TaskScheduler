package dag;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * this class DOESNT contain solid information of a NODE, it just explains relationship for better scalability,
 * developer should fetch solid info of the node in runtime...
 * can find father nodes and son nodes via this one
 * @author xiaohan
 * @since 2020/1/23 11:29 AM
 */
public class TaskNode {

    //the task class simple name
    private String taskId;

    private Set<TaskNode> fatherNodes = new HashSet<>();

    private Set<TaskNode> sonNodes = new HashSet<>();

    public String getTaskId() {
        return taskId;
    }

    public TaskNode setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    void addSonNode(TaskNode son){
        if (!sonNodes.contains(son)) {
            sonNodes.add(son);
            son.addFatherNode(this);
        }
    }

    void addFatherNode(TaskNode father){
        if (!fatherNodes.contains(father)) {
            fatherNodes.add(father);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        TaskNode taskNode = (TaskNode)o;
        return taskId.equals(taskNode.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}

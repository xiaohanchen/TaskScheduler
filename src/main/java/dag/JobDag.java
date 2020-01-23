package dag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jdk.internal.org.jline.utils.ShutdownHooks.Task;

/**
 * @author xiaohan
 * @since 2020/1/23 11:32 AM
 */
public class JobDag implements DagGraph<String, TaskNode> {

    //not necessary as later may add father nodes to these initNodes, tedious to handle this list
    //Set<TaskNode> initNodes = new HashSet<>(1);

    HashMap<String, TaskNode> allNodes = new HashMap<>(6);

    @Override
    public List<TaskNode> getInitNodes() {
        return allNodes.values().stream().filter(x -> x.getFatherNodes().size() == 0).collect(Collectors.toList());
    }

    @Override
    public List<TaskNode> getAllNodes() {
        return new ArrayList<>(allNodes.values());
    }

    @Override
    public TaskNode getNodes(String key) {
        return null;
    }

    @Override
    public String printGraph() {
        return null;
    }
    
    @Override
    public void addStandaloneNode(String nodeKey) {
        //use the Id to find the TaskNode in the execution
        if(allNodes.get(nodeKey) == null){
            TaskNode taskNode = new TaskNode(nodeKey);
            initNodes.add(taskNode);
            allNodes.put(nodeKey, taskNode);
        }
    }

    @Override
    public void addEdgeNode(String firstNode, String nextNode) {
        TaskNode fatherNode = getOrAdd(firstNode);
        TaskNode sonNode = getOrAdd(nextNode);

        initNodes.add()


    }


    TaskNode getOrAdd(String key){
        if(allNodes.get(key) == null){
            TaskNode taskNode = new TaskNode(key);
            allNodes.put(key, taskNode);
        }
        return allNodes.get(key);
    }

}

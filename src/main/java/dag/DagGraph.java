package dag;

import java.util.List;

/**
 * @author xiaohan
 * @since 2020/1/23 10:38 AM
 */
public interface DagGraph<K,T> extends DependentAware<K> {

    /**
     * get initial standalone nodes
     * @return
     */
    List<T> getInitNodes();

    /**
     * get all nodes
     * @return
     */
    List<T> getAllNodes();

    /**
     * get one node
     * @return
     */
    T getNodes(K key);

    /**
     * print graph for view
     * @return
     */
    String printGraph();
}

package dag;

/**
 * K is the node id, better to target the node
 * the id is helpful, coz you dont normally have the full detail of the node, which could be get later in service
 * @author xiaohan
 * @since 2020/1/23 10:49 AM
 */
public interface DependentAware<K> {

    /**
     * add independent node
     * @param nodeId
     */
    void addStandaloneNode(K nodeId);

    /**
     * add dependent node
     * @param firstNodeId father node
     * @param nextNodeId son node
     */
    void addEdgeNode(K firstNodeId, K nextNodeId);
}

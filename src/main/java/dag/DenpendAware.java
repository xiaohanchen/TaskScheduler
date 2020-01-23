package dag;

/**
 * @author xiaohan
 * @since 2020/1/23 10:49 AM
 */
public interface DenpendAware<T> {

    /**
     * add independent node
     * @param node
     */
    void addStandaloneNode(T node);

    /**
     * add dependent node
     * @param firstNode father node
     * @param nextNode son node
     */
    void addEdgeNode(T firstNode, T nextNode);
}

package dag;

import org.graalvm.compiler.nodes.calc.AddNode;

/**
 * @author xiaohan
 * @since 2020/1/23 10:38 AM
 */
public interface DagGraph {

    void addNode();

    String printGraph();
}

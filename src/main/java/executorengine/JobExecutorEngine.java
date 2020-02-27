package executorengine;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * execution API, can be single thread/multi thread/ or in distributed system
 *
 * @author xiaohan
 * @since 2020/1/31 5:51 PM
 */
public interface JobExecutorEngine {

    /**
     * should call jobExeService's inner process logic
     * @param task
     * @return
     */
    boolean execute(Runnable task);

    /**
     * task with output
     * @param task R is the return type
     * @return
     */
    <R> Future<R>  execute(Callable<R> task);
}

package executorengine;

import java.lang.annotation.Retention;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import tasks.output.TaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/27 9:48 PM
 */
@Component
public class ThreadPoolJobExecutorEngine implements JobExecutorEngine {

    @Resource
    private ThreadPoolExecutor dagTaskThreadPool;

    @Override
    public boolean execute(Runnable task) {
        dagTaskThreadPool.execute(task);
        // TODO: 2020/2/27 add log in DB
        return true;
    }


    @Override
    public <R> Future<R> execute(Callable<R> task) {
        Future<R> future = dagTaskThreadPool.submit(task);
        // TODO: 2020/2/27 add log in DB
        return future;
    }

}

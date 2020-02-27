package threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * global threadpool management
 * @author xiaohan
 * @since 2020/2/27 9:51 PM
 */
@Configuration
public class ThreadPool {

    @Bean
    public ThreadPoolExecutor dagTaskThreadPool(){

        return new ThreadPoolExecutor(
            1,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    }
}

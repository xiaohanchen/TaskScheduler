package tasks;

import tasks.output.TaskOutput;

/**
 * T context
 * R output
 * the class name is the task Id
 * @author xiaohan
 * @since 2020/2/16 4:15 PM
 */
public interface SingleTaskService<T, R extends TaskOutput> {

    R runTask(T context);
}

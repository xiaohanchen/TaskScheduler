package cache.facotry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cache.AbstractFactory;
import cache.Factory;
import org.graalvm.compiler.nodes.memory.MemoryCheckpoint.Single;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.stereotype.Component;
import tasks.SingleTaskService;

import static cache.FactoryNameConstant.TASK_FACTORY;

/**
 * 返回task service
 * @author xiaohan
 * @since 2020/2/16 4:42 PM
 */
@Component
public class TaskServiceFactory extends AbstractFactory<String, SingleTaskService> {

    private HashMap<String, SingleTaskService> taskMap = new HashMap<>(8);


    @Override
    public SingleTaskService getKey(String key) {
        return taskMap.get(key);
    }

    @Override
    public String factoryName() {
        return TASK_FACTORY;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, SingleTaskService> factoryMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(
            super.context, SingleTaskService.class, true, true);

        for (Entry<String, SingleTaskService> stringAbstractFactoryEntry : factoryMap.entrySet()) {
            SingleTaskService taskService = stringAbstractFactoryEntry.getValue();
            this.taskMap.put(taskService.getClass().getSimpleName(), taskService);
        }
    }
}

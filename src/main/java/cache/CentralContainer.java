package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import dag.JobDag;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import tasks.SingleTaskService;

import static cache.FactoryNameConstant.TASK_FACTORY;

/**
 * container to store things at startup
 * @author xiaohan
 * @since 2020/2/16 4:14 PM
 */
@Component
public class CentralContainer implements InitializingBean, ApplicationContextAware {
    private ApplicationContext context;

    private HashMap<String, Factory> factoryMap = new HashMap<>(8);

    public SingleTaskService getTask(String taskId){
        return (SingleTaskService)factoryMap.get(TASK_FACTORY).getKey(taskId);
    }

    public JobDag getJobDag(String jobId){
        return (JobDag)factoryMap.get(TASK_FACTORY).getKey(jobId);
    }



    /**
     * init factoryMap
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet(){
        Map<String, Factory> factoryMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(
            this.context, Factory.class, true, true);

        for (Entry<String, Factory> stringAbstractFactoryEntry : factoryMap.entrySet()) {
            Factory factory = stringAbstractFactoryEntry.getValue();
            this.factoryMap.put(factory.factoryName(), factory);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

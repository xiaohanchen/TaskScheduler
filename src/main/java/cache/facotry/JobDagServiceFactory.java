package cache.facotry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cache.AbstractFactory;
import dag.JobDag;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Component;
import tasks.SingleTaskService;
import tasks.instance.AlyssaSaysTaskService;
import tasks.instance.CountWordsTaskService;
import tasks.instance.JameSaysTaskService;

import static cache.FactoryNameConstant.JOB_DAG_FACTORY;
import static cache.FactoryNameConstant.TASK_FACTORY;

/**
 * 返回task service
 * @author xiaohan
 * @since 2020/2/16 4:42 PM
 */
@Component
public class JobDagServiceFactory extends AbstractFactory<String, JobDag> {

    private HashMap<String, JobDag> jobDagMap = new HashMap<>(8);


    @Override
    public JobDag getKey(String key) {
        return jobDagMap.get(key);
    }

    @Override
    public String factoryName() {
        return JOB_DAG_FACTORY;
    }

    /**
     * here we should read from persistence for jobIds,
     * then use preconfig to find jobIds' tasksIds,
     * after that assemble JobDag
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        //skip reading persistence part..

        JobDag jobDag = new JobDag();
        jobDag.addEdgeNode(JameSaysTaskService.class.getSimpleName(), CountWordsTaskService.class.getSimpleName());
        jobDag.addEdgeNode(AlyssaSaysTaskService.class.getSimpleName(), CountWordsTaskService.class.getSimpleName());
        jobDagMap.put("JOB_COUNT_WORDS", jobDag);

    }
}

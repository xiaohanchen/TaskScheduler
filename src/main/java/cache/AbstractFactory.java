package cache;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import tasks.SingleTaskService;

/**
 * @author xiaohan
 * @since 2020/2/16 4:46 PM
 */
public abstract class AbstractFactory<T,R> implements Factory<T, R>, ApplicationContextAware, InitializingBean {

    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}

package cache;

/**
 * @author xiaohan
 * @since 2020/2/16 4:17 PM
 */
public interface Factory<T,R> {

    R getKey(T key);

    String factoryName();
}

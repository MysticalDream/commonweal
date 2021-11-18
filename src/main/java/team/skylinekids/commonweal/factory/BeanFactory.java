package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.factory.exception.BeanException;

import java.util.HashMap;
import java.util.Map;

/**
 * bean工厂
 *
 * @author MysticalDream
 */
public final class BeanFactory {

    private static Map<String, Object> BEAN_CACHE = new HashMap<>();

    private BeanFactory() {

    }

    public static Object getBean(String key) {
        return BeanFactory.getBean(key, null);
    }

    /**
     * 获取bean对象
     *
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(String key, Class<T> tClass) {
        T o = (T) BEAN_CACHE.get(key);
        if (o != null) {
            return o;
        }
        if (tClass == null) {
            return null;
        }
        //双重加锁
        synchronized (BEAN_CACHE) {
            o = (T) BEAN_CACHE.get(key);
            if (o == null) {
                try {
                    o = tClass.getConstructor().newInstance();
                    BEAN_CACHE.put(key, o);
                } catch (Exception e) {
                    throw new BeanException("反射构造bean异常");
                }
            }
        }
        return o;
    }

}

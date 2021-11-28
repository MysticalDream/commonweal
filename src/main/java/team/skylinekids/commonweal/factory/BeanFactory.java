package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.factory.exception.BeanException;
import team.skylinekids.commonweal.factory.exception.DaoBeanException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * bean工厂
 *
 * @author MysticalDream
 */
public final class BeanFactory {

    private static final Map<String, Object> BEAN_CACHE = new HashMap<>();

    private BeanFactory() {

    }

    public static Object getBean(String key) {
        return BeanFactory.getBean(key, null);
    }

    /**
     * 获取bean对象
     *
     */
    @SuppressWarnings("unchecked")
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
                    throw new BeanException("反射构造bean异常",e);
                }
            }
        }
        return o;
    }

    /**
     * 从配置文件读取
     *
     */
    public static Object getBeanPro(String key, String filePath) {
        Object bean = BeanFactory.getBean(key);
        if (bean == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream(filePath);
                properties.load(inputStream);
                String clazz = properties.getProperty(key);
                bean = BeanFactory.getBean(key, Class.forName(clazz));
            } catch (Exception e) {
                throw new DaoBeanException();
            }
        }
        return bean;
    }

}

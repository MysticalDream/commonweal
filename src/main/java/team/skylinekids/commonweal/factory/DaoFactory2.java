package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.factory.exception.DaoBeanException;

import java.io.InputStream;
import java.util.Properties;

/**
 * dao工厂
 *
 * @author MysticalDream
 */
public class DaoFactory2 {

    private static String PATH = "daoConfig.properties";

    private DaoFactory2() {
    }


    /**
     * 根据类型获取dao的实现类
     *
     * @param tClass
     * @return
     */
    public static <T> T getDaoImpl(Class<T> tClass) {
        Object daoImpl = getDaoImpl(tClass.getSimpleName(), PATH);
        if (tClass.isInstance(daoImpl)) {
            return (T) daoImpl;
        }
        throw new DaoBeanException("获取dao异常");
    }

    /**
     * 根据键获取Dao
     *
     * @param key
     * @return
     */
    public static Object getDaoImpl(String key) {
        return getDaoImpl(key, PATH);
    }

    /**
     * 根据键和配置文件路径获取dao的实现类
     *
     * @param key
     * @param filePath
     * @return
     */
    public static Object getDaoImpl(String key, String filePath) {
        Object bean = BeanFactory.getBean(key);
        if (bean == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = DaoFactory2.class.getClassLoader().getResourceAsStream(filePath);
                properties.load(inputStream);
                String daoClass = properties.getProperty(key);
                bean = BeanFactory.getBean(key, Class.forName(daoClass));
            } catch (Exception e) {
                throw new DaoBeanException();
            }
        }
        return bean;
    }
}

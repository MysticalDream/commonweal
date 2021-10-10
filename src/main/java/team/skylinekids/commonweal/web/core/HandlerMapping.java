package team.skylinekids.commonweal.web.core;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.RequestType;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;
import team.skylinekids.commonweal.web.core.exception.HandlerException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 映射器（url和方法对应）
 *
 * @author MysticalDream
 */
public final class HandlerMapping {

    private static final Logger logger = Logger.getLogger(HandlerMapping.class);

    private HandlerMapping() {

    }

    /**
     * 地址和controller中的方法信息映射
     */
    private static Map<String, MyMethodInfo> mapData = new ConcurrentHashMap<>();


    public static MyMethodInfo get(String uri) {
        return mapData.get(uri);
    }

    /**
     * 处理映射
     *
     * @param classSet
     */
    public static void handler(Set<Class<?>> classSet) {

        try {
            for (Class<? extends Object> clazz :
                    classSet) {

                MyRequestPath clazzDeclaredAnnotation = clazz.getDeclaredAnnotation(MyRequestPath.class);

                //获取类上的注解的路径
                String value = getURLString(clazzDeclaredAnnotation);
                if (value == null) {
                    continue;
                }
                String root = value;
                /**
                 * 实例化对象
                 */
                Object o = clazz.getConstructor().newInstance();
                /**
                 * 获取该类下的所有方法
                 */
                Method[] methods = clazz.getDeclaredMethods();

                for (Method method :
                        methods) {
                    //获取方法下的所有注解
                    MyRequestPath methodDeclaredAnnotation = method.getDeclaredAnnotation(MyRequestPath.class);

                    if (methodDeclaredAnnotation != null) {
                        String urlString = getURLString(methodDeclaredAnnotation);
                        //地址
                        String urlMapping = root + urlString;
                        /**
                         * 请求类型
                         */
                        RequestType[] type = methodDeclaredAnnotation.type();

                        MyMethodInfo myMethodInfo = new MyMethodInfo(o, method, type);

                        if (mapData.put(urlMapping, myMethodInfo) != null) {
                            throw new HandlerException("请求地址重复:" + urlMapping);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取RequestMapping注解上的路径
     *
     * @param clazzDeclaredAnnotation
     * @return
     * @throws HandlerException
     */
    private static String getURLString(MyRequestPath clazzDeclaredAnnotation) throws HandlerException {
        if (clazzDeclaredAnnotation == null) {
            return null;
        }
        String value = clazzDeclaredAnnotation.value();
        //value值为空时去判断urlPattern
        if (value == null || "".equals(value.trim())) {
            //获取urlPattern
            value = clazzDeclaredAnnotation.urlPattern();
            //还是空时抛出异常
            if (value == null || "".equals(value.trim())) {
                throw new HandlerException("RequestMapping没有配置url");
            }
        }
        return value;
    }
}

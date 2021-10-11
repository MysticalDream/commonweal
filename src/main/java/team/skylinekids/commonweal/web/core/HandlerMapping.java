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
    public static void handle(Set<Class<?>> classSet) {

        try {
            for (Class<? extends Object> clazz :
                    classSet) {

                MyRequestPath clazzDeclaredAnnotation = clazz.getDeclaredAnnotation(MyRequestPath.class);

                //获取类上的注解的路径
                String value = getURLString(clazzDeclaredAnnotation);
                //根路径
                String root = value == null ? "" : value;
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
                        //该注解上的路径
                        String urlString = getURLString(methodDeclaredAnnotation);
                        //地址
                        String urlMapping = root + urlString;
                        /**
                         * 请求类型
                         */
                        RequestType[] type = methodDeclaredAnnotation.type();

                        MyMethodInfo myMethodInfo = new MyMethodInfo(o, method, type);

                        if ((myMethodInfo = mapData.putIfAbsent(urlMapping, myMethodInfo)) != null) {
                            //地址相同，看看请求类型是否相同
                            if (!myMethodInfo.insertRequestTypeMethodMap(type, method)) {
                                throw new HandlerException("同一个请求地址:" + urlMapping + "中有同一个请求类型对应了多个方法");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取MyRequestPath注解上的路径
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
                throw new HandlerException("MyRequestPath没有配置url");
            }
        }
        return value;
    }
}

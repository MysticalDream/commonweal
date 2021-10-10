package team.skylinekids.commonweal.utils;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 转换工具
 *
 * @author MysticalDream
 */
public class ConversionUtils {

    private final static Logger logger = Logger.getLogger(ConversionUtils.class);

    /**
     * pojo类之间同名域的转化
     *
     * @param source
     * @param clazz
     * @return
     */
    public static <T> T convert(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        try {
            T target = clazz.getConstructor().newInstance();
            Field[] fields = source.getClass().getDeclaredFields();
            for (Field field :
                    fields) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                try {
                    clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException exception) {
                    continue;
                }
                String common = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                String setMethodName = "set" + common;
                String getMethodName = "get" + common;
                Method methodSet = clazz.getMethod(setMethodName, fieldType);
                Method methodGet = source.getClass().getMethod(getMethodName);
                methodSet.invoke(target, methodGet.invoke(source));
            }
            return target;
        } catch (Exception e) {
            logger.error("实体类转化异常", e);
        }
        return null;
    }

}

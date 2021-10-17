package team.skylinekids.commonweal.utils.convert;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 转换工具
 *
 * @author MysticalDream
 */
public class ConversionUtils {

    private final static Logger logger = Logger.getLogger(ConversionUtils.class);

    /**
     * @description 接口不能实例化只有当它是静态的才有意义
     */
    public interface Custom<T> {
        /**
         * 修改get返回值用到set
         *
         * @param source 原对象
         * @param field  当前要修改的域，get方法取值的域
         * @param result get方法结果
         * @return
         */
        Object customValue(Object source, Field field, Object result);

        /**
         * 获取当前要转化（目标的）对应的类型
         *
         * @param fieldName 当前处理的域名
         * @return
         */
        Class<T> getType(String fieldName);
    }

    /**
     * 列表转化
     *
     * @param srcList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertList(List<?> srcList, Class<T> clazz) {
        List<T> tList = new ArrayList<>(srcList.size());
        for (Object e : srcList) {
            T convert = convert(e, clazz);
            if (convert instanceof ItemDTO && e instanceof Item) {
                Integer id = ((Item) e).getItemCategoryId();
                String categoryName = CategoryUtils.getCategoryNameById(id.intValue());
                ((ItemDTO) convert).setItemCategory(categoryName);
            }
            tList.add(convert);
        }
        return tList;
    }

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
                IgnoreConvert annotation = field.getDeclaredAnnotation(IgnoreConvert.class);
                if (annotation != null) {
                    continue;
                }
                if (convertField(source, clazz, target, field)) {
                    continue;
                }
            }
            return target;
        } catch (Exception e) {
            logger.error("实体类转化异常", e);
        }
        return null;
    }

    private static <T> boolean convertField(Object source, Class<T> clazz, T target, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String fieldName = field.getName();
        Class<?> fieldType = field.getType();
        try {
            clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException exception) {
            return true;
        }
        String common = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String setMethodName = "set" + common;
        String getMethodName = "get" + common;
        Method methodSet = clazz.getMethod(setMethodName, fieldType);
        Method methodGet = source.getClass().getMethod(getMethodName);
        Object result = methodGet.invoke(source);
        methodSet.invoke(target, result);
        return false;
    }

    public static <T, S> T convert(Object source, Class<T> clazz, Custom<S> custom) {
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

                Class<S> type = custom.getType(fieldName);

                if (type != null) {
                    fieldType = type;
                }
                Method methodSet = clazz.getMethod(setMethodName, fieldType);
                Method methodGet = source.getClass().getMethod(getMethodName);
                Object result = methodGet.invoke(source);
                //调用要处理get返回值的函数
                result = custom.customValue(source, field, result);
                methodSet.invoke(target, result);
            }
            return target;
        } catch (Exception e) {
            logger.error("实体类转化异常", e);
        }
        return null;
    }

    /**
     * 将对象属性放到map中
     * 值是属性值的字符串形式
     *
     * @param o
     * @return
     */
    public static Map oToStringMap(Object o) {
        Map<String, String> stringMap = new HashMap<>(16);
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            for (Field field :
                    fields) {
                field.setAccessible(true);
                stringMap.put(field.getName(), GsonUtils.o2J(field.get(o)));
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return stringMap;
    }

    /**
     * 向字符列表添加分隔符合并成字符串
     *
     * @param list
     * @param delimiter
     * @return
     */
    public static String listToStringWithSeparator(List<? extends CharSequence> list, String delimiter) {
        return String.join(delimiter, list);
    }

    /**
     * 把具有分隔符的字符串分割放到list集合
     *
     * @param s
     * @param delimiter
     * @return
     */
    public static List<String> stringWithSeparatorToList(String s, String delimiter) {
        return Arrays.asList(s.split(delimiter));
    }
}

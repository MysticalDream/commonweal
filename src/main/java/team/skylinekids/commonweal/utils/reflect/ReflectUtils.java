package team.skylinekids.commonweal.utils.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 *
 * @author MysticalDream
 */
public class ReflectUtils {
    /**
     * 私有化构造器
     */
    private ReflectUtils() {

    }

    /**
     * 获取pojo类及其父类的字段域
     *
     * @param obj 实体对象
     * @param <T> 实体类型
     * @return 返回实体类中的所有及其父类的属性数组
     */
    public static <T> Field[] getAllFields(T obj) {
        if (obj == null) {
            return null;
        }
        Class<?> clazz = obj.getClass();
        List<Field> list = new ArrayList<>();
        while (clazz != null) {
            list.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[list.size()];
        list.toArray(fields);
        setAccessFields(fields);
        return fields;
    }

    /**
     * 获取pojo类及其父类的非空字段域
     *
     * @param obj 实体对象
     * @param <T> 对象类型
     * @return
     */
    public static <T> Field[] getAllNotNullFields(T obj) {
        if (obj == null) {
            return null;
        }
        Class<?> clazz = obj.getClass();
        List<Field> list = new ArrayList<>();
        try {
            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field :
                        fields) {
                    field.setAccessible(true);
                    Object o = field.get(obj);
                    if (o != null) {
                        list.add(field);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[] fields = new Field[list.size()];
        list.toArray(fields);
        return fields;
    }

    /**
     * 根据类类型获取pojo类及其父类的字段域
     *
     * @param clazz 类类型
     * @return
     */
    public static Field[] getAllFields(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        List<Field> list = new ArrayList<>();
        while (clazz != null) {
            list.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[list.size()];
        list.toArray(fields);
        setAccessFields(fields);
        return fields;
    }

    /**
     * 取消java语言访问检查
     *
     * @param fields 实体类中的属性
     */
    public static void setAccessFields(Field[] fields) {
        if (fields == null) {
            return;
        }
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
        }
    }

    /**
     * 收集非空值属性到列表
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> List<Object> collectNonNullValuesToList(T entity) {

        Field[] allFields = getAllFields(entity);
        List<Object> list = new ArrayList<>(allFields.length);
        try {
            for (Field field :
                    allFields) {
                IgnoreCollection annotation = field.getDeclaredAnnotation(IgnoreCollection.class);
                if (annotation != null) {
                    continue;
                }
                if (field.get(entity) != null) {
                    list.add(field.get(entity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

package team.skylinekids.commonweal.utils;


import team.skylinekids.commonweal.dao.core.annotaion.TableField;
import team.skylinekids.commonweal.dao.core.annotaion.TableId;
import team.skylinekids.commonweal.utils.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * sql语句工具来
 *
 * @author MysticalDream
 */
public final class SqlUtils {

    private SqlUtils() {

    }

    /**
     * 字符串是否是空白
     *
     * @param str
     * @return
     */
    private static boolean isBank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 小驼峰式的实体类的属性名转换成数据表字段名
     * 如:userId---->user_id
     *
     * @param camelCaseStr 小驼峰式属性名
     * @return 返回转发后的字符串
     */
    private static String decamelize(String camelCaseStr) {
        return isBank(camelCaseStr) ? camelCaseStr : camelCaseStr.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 获取查询的列
     *
     * @param fields
     * @param flag   为true时为查询语句添加 as 别名
     * @return
     */
    private static List<String> getColumns(Field[] fields, boolean flag) {
        List<String> list = new ArrayList<>();
        if (fields == null) {
            return null;
        }
        for (Field field :
                fields) {
            TableField fieldAnnotation = field.getDeclaredAnnotation(TableField.class);
            if (fieldAnnotation != null) {
                list.add(fieldAnnotation.value() + (flag ? " as " + field.getName() : ""));
            } else {
                list.add(decamelize(field.getName()) + (flag ? " as " + field.getName() : ""));
            }
        }
        return list;
    }

    /**
     * 获取值非空属性名列表
     *
     * @param fields 字段域
     * @param entity 实体
     * @param <T>    实体类型
     * @return
     */
    private static <T> List<String> getNotNullFields(Field[] fields, T entity) {
        if (fields == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        try {
            for (Field field :
                    fields) {
                Object o = field.get(entity);
                if (o == null) {
                    continue;
                }
                TableField fieldAnnotation = field.getDeclaredAnnotation(TableField.class);
                if (fieldAnnotation != null) {
                    list.add(fieldAnnotation.value());
                } else {
                    list.add(decamelize(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取值非空且可插入属性名列表
     *
     * @param fields
     * @param entity
     * @param <T>
     * @return
     */
    private static <T> List<String> getNotNullAndInsertableFields(Field[] fields, T entity) {
        if (fields == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        try {
            for (Field field :
                    fields) {
                Object o = field.get(entity);
                if (o == null) {
                    continue;
                }
                TableField fieldAnnotation = field.getDeclaredAnnotation(TableField.class);
                if (fieldAnnotation != null) {
                    if (fieldAnnotation.insert()) {
                        list.add(fieldAnnotation.value());
                    }
                } else {
                    list.add(decamelize(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取非空值
     *
     * @param fields 字段域
     * @param entity 实体
     * @param <T>    实体类型
     * @return
     */
    private static <T> List<Object> getNotNullFieldValues(Field[] fields, T entity) {
        List<Object> list = new ArrayList<>();
        try {
            for (Field field :
                    fields) {
                Object o = field.get(entity);
                if (o == null) {
                    continue;
                }
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取非空且可以插入的列的值列表
     *
     * @param fields
     * @param entity
     * @param <T>
     * @return
     */
    private static <T> List<Object> getNotNullAndInsertableFieldValues(Field[] fields, T entity) {
        List<Object> list = new ArrayList<>();
        try {
            for (Field field :
                    fields) {
                Object o = field.get(entity);
                if (o == null) {
                    continue;
                }
                TableField tableField = field.getDeclaredAnnotation(TableField.class);
                if (tableField != null) {
                    if (tableField.insert()) {
                        list.add(o);
                    }
                } else {
                    list.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 条件添加预编译占位符
     *
     * @param list 作为条件的列表
     * @return 返回添加占位符后的条件列表
     */
    private static List<String> addQM(List<String> list) {
        if (list == null) {
            return null;
        }
        List<String> list1 = new ArrayList<>();
        for (String str :
                list) {
            list1.add(" " + str + "=? ");
        }
        return list1;
    }

    /**
     * 获取select语句的查询列
     *
     * @param fields
     * @param flag   为true时为查询语句添加 as 别名
     * @return
     */
    public static String getSelectColumnsByField(Field[] fields, boolean flag) {
        List<String> list = getColumns(fields, flag);
        if (list == null) {
            return null;
        }
        return String.join(",", list);
    }

    /**
     * 获取插入语句的实体非空值对应的列名
     *
     * @param fields
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String getInsertNotNullColumns(Field[] fields, T entity) {
        List<String> list = getNotNullAndInsertableFields(fields, entity);
        if (list == null || list.size() == 0) {
            return null;
        }
        //将列的数量加到要插入列的字符串的第一位
        return list.size() + ";" + String.join(",", list);
    }

    /**
     * 根据传进来的数目产生对应数量的展位符
     * 比如传入 3
     * 返回?,?,?
     *
     * @param number
     * @return
     */
    public static String getQMByNumber(Number number) {
        String[] strings = new String[number.intValue()];
        Arrays.fill(strings, "?");
        return String.join(",", strings);
    }

    /**
     * 根据实体类获取条件
     *
     * @param entity 查询实体
     * @param <T>    实体类型
     * @return 返回预编译条件
     */
    public static <T> String getPreConditionString(T entity, String operator) {
        if (entity == null) {
            return null;
        }
        Field[] allFields = ReflectUtils.getAllFields(entity);
        List<String> notNullColumns = getNotNullFields(allFields, entity);
        List<String> list = addQM(notNullColumns);
        return String.join(operator, list);
    }


    /**
     * 获取占位符的值
     *
     * @param entity 查询条件实体
     * @param <T>    实体类型
     * @return 返回实体类非空属性的值列表
     */
    public static <T> List<Object> getNotNullValueList(T entity) {
        if (entity == null) {
            return null;
        }
        Field[] allFields = ReflectUtils.getAllFields(entity);
        List<Object> notNullColumnsValue = getNotNullFieldValues(allFields, entity);
        return notNullColumnsValue;
    }

    /**
     * 获取可插入的占位符的值
     *
     * @param entity 实体对象
     * @param <T>    对象类型
     * @return
     */
    public static <T> List<Object> getNotNullAndInsertableValueList(T entity) {
        if (entity == null) {
            return null;
        }
        Field[] allFields = ReflectUtils.getAllFields(entity);
        List<Object> notNullColumnsValue = getNotNullFieldValues(allFields, entity);
        return notNullColumnsValue;
    }

    /**
     * 获取实体类对应数据库表的主键字段名
     *
     * @param clazz
     * @return
     */
    public static String getTableIdColumn(Class<?> clazz) {
        Field[] fields = ReflectUtils.getAllFields(clazz);
        String columnName = null;
        for (Field field :
                fields) {
            TableId tableId = field.getDeclaredAnnotation(TableId.class);
            if (tableId != null) {
                TableField fieldAnnotation = field.getDeclaredAnnotation(TableField.class);
                if (fieldAnnotation != null) {
                    columnName = fieldAnnotation.value();
                } else {
                    columnName = decamelize(field.getName());
                }
                break;
            }
        }
        return columnName;
    }

    /**
     * 获取主键预编译条件
     *
     * @param clazz
     * @return
     */
    public static String getTableIdColumnPrevCondition(Class<?> clazz) {
        String s = SqlUtils.getTableIdColumn(clazz);
        List<String> list = SqlUtils.addQM(new ArrayList<>(Arrays.asList(new String[]{s})));
        return list.get(0);
    }

    /**
     * 获取非空的可更新的列表
     *
     * @param entity 实体对像
     * @param <T>    对象类型
     * @return
     */
    private static <T> List<String> getNotNullUpdatableColumnsNameList(T entity) {
        List<String> list = new ArrayList<>();
        Field[] fields = ReflectUtils.getAllNotNullFields(entity);
        for (Field field :
                fields) {
            TableField annotation = field.getDeclaredAnnotation(TableField.class);
            if (annotation != null) {
                if (annotation.update()) {
                    if (!"".equals(annotation.value())) {
                        list.add(annotation.value());
                    } else {
                        list.add(decamelize(field.getName()));
                    }
                }
            } else {
                list.add(decamelize(field.getName()));
            }
        }
        return list;
    }

    /**
     * 获取非空属性名和值组成更新语句
     *
     * @param entity 实体对象
     * @param <T>    实体类型
     * @return
     */
    public static <T> String getUpdateColumnList(T entity) {
        List<String> stringList = getNotNullUpdatableColumnsNameList(entity);
        List<String> list = addQM(stringList);
        return String.join(",", list);
    }

    public static <T> List<Object> getUpdateValueList(T entity) throws IllegalAccessException {
        List<Object> list = new ArrayList<>();
        List<Object> list1 = getNotNullUpdatableValueList(entity);
        list1.add(getPrimaryKeyValues(entity));
        return list1;
    }

    /**
     * 获取可更新的列的非空值
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> List<Object> getNotNullUpdatableValueList(T entity) throws IllegalAccessException {
        if (entity == null) {
            return null;
        }
        Field[] allFields = ReflectUtils.getAllFields(entity);
        List<Object> list = new ArrayList<>(allFields.length - 1);
        for (Field field :
                allFields) {
            Object o = field.get(entity);
            if (o == null) {
                continue;
            }
            TableField fieldDeclaredAnnotation = field.getDeclaredAnnotation(TableField.class);
            if (fieldDeclaredAnnotation != null) {
                if (fieldDeclaredAnnotation.update()) {
                    list.add(o);
                }
            } else {
                list.add(o);
            }
        }
        return list;
    }

    /**
     * 获取主键值
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> Object getPrimaryKeyValues(T entity) {
        Field[] fields = ReflectUtils.getAllNotNullFields(entity);
        try {
            for (Field field :
                    fields) {
                TableId tableId = field.getDeclaredAnnotation(TableId.class);
                if (tableId != null) {
                    Object o = field.get(entity);
                    return o;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 设置主键值
     *
     * @param entity
     * @param value
     * @param <T>
     */
    public static <T> void setPrimaryKeyValue(T entity, Object value) {
        Field[] fields = ReflectUtils.getAllFields(entity);
        try {
            for (Field field :
                    fields) {
                TableId tableId = field.getDeclaredAnnotation(TableId.class);
                if (tableId != null) {
                    field.set(entity, value);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

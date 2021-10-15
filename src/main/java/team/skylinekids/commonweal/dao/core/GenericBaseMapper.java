package team.skylinekids.commonweal.dao.core;


import team.skylinekids.commonweal.dao.core.annotaion.TableName;
import team.skylinekids.commonweal.utils.JDBCUtils;
import team.skylinekids.commonweal.utils.ReflectUtils;
import team.skylinekids.commonweal.utils.SqlUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 抽象类通用mapper
 *
 * @author MysticalDream
 */
public abstract class GenericBaseMapper<T> extends BaseDao<T> implements BaseMapper<T> {

    /**
     * 数据库表名
     */
    private final String tableName;

    //反射获取实体类注解的对应数据库表名
    {
        TableName tableNameAnnotation = super.type.getDeclaredAnnotation(TableName.class);
        if (tableNameAnnotation != null) {
            tableName = tableNameAnnotation.value();
        } else {
            //默认类名小写
            tableName = super.type.getSimpleName().toLowerCase();
        }

    }

    @Override
    public Integer insert(T entity) throws SQLException {

        if (entity == null) {
            throw new NullPointerException("参数entity不能为空");
        }
        //插入语句
        String insertSql = "INSERT INTO " + this.tableName + " ({0}) VALUES ({1})";
        //插入列名
        String insertCol = SqlUtils.getInsertNotNullColumns(ReflectUtils.getAllNotNullFields(entity), entity);

        if (insertCol == null) {
            return 0;
        }
        //获取要插入的列的数目
        int count = Integer.parseInt(insertCol.substring(0, insertCol.indexOf(";")));
        //截取出要插入的列
        insertCol = insertCol.substring(insertCol.indexOf(";") + 1);

        insertSql = MessageFormat.format(insertSql, insertCol, SqlUtils.getQMByNumber(count));

        logger.info("===>   Preparing:" + insertSql);


        Object[] array = SqlUtils.getNotNullValueList(entity).toArray();

        logger.info("===>    Parameters:" + Arrays.toString(array));

        Connection connection = JDBCUtils.getConnection();

        int i = this.update(connection, insertSql, array);

        return i;
    }

    @Override
    public Integer update(T entity) throws Exception {
        if (entity == null) {
            logger.error("实体对象不能为空");
            throw new NullPointerException("实体对象不能为空");
        }
        //更新字符串
        String updateSql = "UPDATE " + this.tableName + " SET {0} WHERE {1}";
        //更新字段语句
        String columnsAndValues = SqlUtils.getUpdateColumnList(entity);
        //更新条件
        String condition = SqlUtils.getTableIdColumnPrevCondition(super.type);

        updateSql = MessageFormat.format(updateSql, columnsAndValues, condition);

        logger.info("===>   Preparing:" + updateSql);

        Connection connection = JDBCUtils.getConnection();

        Object[] array = SqlUtils.getUpdateValueList(entity).toArray();

        logger.info("===>   Parameters:" + Arrays.toString(array));
        int update = this.update(connection, updateSql, array);
        return update;
    }

    @Override
    public Integer deleteByPrimaryKey(Object key) throws SQLException {
        if (key == null) {
            logger.error("key键不能为空");
            throw new NullPointerException("key键不能为空");
        }
        //删除SQL语句
        String deleteSql = "DELETE FROM " + this.tableName + " WHERE {0}";
        //条件语句
        String condition = SqlUtils.getTableIdColumnPrevCondition(super.type);
        //拼接好的sql语句
        deleteSql = MessageFormat.format(deleteSql, condition);

        logger.info("===>   Preparing:" + deleteSql);
        logger.info("===>   Parameters:" + key);

        Connection connection = JDBCUtils.getConnection();

        int update = this.update(connection, deleteSql, key);

        return update;
    }

    @Override
    public Integer delete(T entity) throws SQLException {
        //删除SQL语句
        String deleteSql = "DELETE FROM " + this.tableName + "{0}";
        //获取预编译字符串
        String condition = SqlUtils.getPreConditionString(entity, "AND");
        if (condition != null) {
            condition = " WHERE " + condition;
        }

        deleteSql = MessageFormat.format(deleteSql, condition);

        logger.info("===>   Preparing:" + deleteSql);
        Object[] array = SqlUtils.getNotNullValueList(entity).toArray();
        logger.info("===>   Parameters:" + Arrays.toString(array));
        Connection connection = JDBCUtils.getConnection();
        int update = this.update(connection, deleteSql, array);
        return update;
    }


    @Override
    public T selectOne(T entity) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //SQL查询语句
        String selectSql = "SELECT {0} FROM " + this.tableName + " WHERE {1}";
        //select查询语句的列
        String columns = SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(entity), true);
        //where语句的预编译字符串
        String preConditionString = SqlUtils.getPreConditionString(entity, "AND");
        //拼接sql语句
        selectSql = MessageFormat.format(
                selectSql,
                columns,
                preConditionString
        );

        //条件不能为空
        if (preConditionString.isBlank()) {
            throw new IllegalArgumentException(
                    "where条件不能为空即实体类属性不能为空\n"
                            + "===>    Preparing:"
                            + selectSql
            );
        }
        logger.info("===>    Preparing:" + selectSql);
        Object[] array = SqlUtils.getNotNullValueList(entity).toArray();
        logger.info("===>    Parameters:" + Arrays.toString(array));
        Connection connection = JDBCUtils.getConnection();
        T bean = this.getBean(connection, selectSql, array);

        return bean;
    }

    @Override
    public List<T> selectList(T entity) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //SQL查询语句
        String selectSql = "SELECT {0} FROM " + this.tableName + "{1}";
        //select查询语句的列
        String columns = SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(entity), true);
        //where语句的预编译字符串
        String preConditionString = " WHERE " + SqlUtils.getPreConditionString(entity, "AND");

        selectSql = MessageFormat.format(
                selectSql,
                columns,
                preConditionString);
        logger.info("===>    Preparing:" + selectSql);
        Object[] array = SqlUtils.getNotNullValueList(entity).toArray();
        logger.info("===>    Parameters:" + Arrays.toString(array));
        Connection connection = JDBCUtils.getConnection();
        List<T> listBean = this.getListBean(connection, selectSql, array);
        return listBean;
    }

    @Override
    public Integer selectCount(T entity) throws SQLException {
        //SQL查询语句
        String selectSql = "SELECT COUNT({0}) FROM " + this.tableName + "{1}";
        //非空列
        String cols = SqlUtils.getSelectColumnsByField(ReflectUtils.getAllNotNullFields(entity), false);
        //全部是空则查询所有
        cols = cols == null || cols.isBlank() ? "*" : cols;
        //where语句的预编译字符串
        String preConditionString = SqlUtils.getPreConditionString(entity, "AND");

        preConditionString =
                preConditionString == null || preConditionString.isBlank()
                        ?
                        ""
                        :
                        " WHERE " + preConditionString;

        selectSql = MessageFormat.format(
                selectSql,
                cols,
                preConditionString
        );
        logger.info("===>    Preparing:" + selectSql);
        Connection connection = JDBCUtils.getConnection();
        List<Object> list = SqlUtils.getNotNullValueList(entity);
        Number value;
        if (list == null) {
            logger.info("===>    Parameters:");
            value = (Number) this.getSingleValue(connection, selectSql);
        } else {
            logger.info("===>    Parameters:" + Arrays.toString(list.toArray()));
            value = (Number) this.getSingleValue(connection, selectSql, list.toArray());
        }
        return value.intValue();
    }

    @Override
    public Integer selectAllCount() throws SQLException {
        return this.selectCount(null);
    }

    @Override
    public List<T> selectAll() throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //SQL查询语句
        String selectSql = "SELECT {0} from " + this.tableName;
        //select查询语句的列
        String columns = SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(super.type), true);
        //拼接sql语句
        selectSql = MessageFormat.format(selectSql, columns);

        logger.info("===>    Preparing:" + selectSql);
        Connection connection = JDBCUtils.getConnection();
        logger.info("===>    Parameters:");
        List<T> listBean = this.getListBean(connection, selectSql);

        return listBean;
    }

    @Override
    public T selectByPrimaryKey(Object key) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        //SQL查询语句
        String selectSql = "SELECT {0} from " + this.tableName + " WHERE {1}";
        //select查询语句的列
        String columns = SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(super.type), true);
        //预编译条件
        String prevCondition = SqlUtils.getTableIdColumnPrevCondition(super.type);
        //拼接查询语句
        selectSql = MessageFormat.format(selectSql, columns, prevCondition);
        logger.info("===>    Preparing:" + selectSql);
        Connection connection = JDBCUtils.getConnection();
        logger.info("===>    Parameters:" + key);
        T bean = this.getBean(connection, selectSql, key);

        return bean;
    }

}

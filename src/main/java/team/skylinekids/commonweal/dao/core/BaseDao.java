package team.skylinekids.commonweal.dao.core;


import org.apache.log4j.Logger;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表操作的基类,封装了数据库操作，减少代码重复，降低耦合性
 *
 * @param <T> 操作对象类型
 * @author MysticalDream
 * @since 1.0
 */
public abstract class BaseDao<T> {

    Logger logger = Logger.getLogger(this.getClass());
    /**
     * 对应数据库表操作类型
     */

    Class<T> type;

    //构造代码块
    {
        //获取直接继承的父类(带有泛型参数)
        // 此处this指其子类
        Type genericSuperclass = this.getClass().getGenericSuperclass();

//      type = this.getParameterizedClassTypeArguments(genericSuperclass)[0];

        //转化为参数化类型
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        //获得直接父类的泛型参数的实际类型
        Type[] types = parameterizedType.getActualTypeArguments();
        //泛型的第一个参数
        type = (Class<T>) types[0];
    }

    /**
     * 获取泛型类类型参数类类型
     *
     * @param type0 类型
     * @return 如果是参数化类型则返回类型参数数组，否则返回null
     * @since 1.0
     * @deprecated 多此一举，直接调用 {@link ParameterizedType#getActualTypeArguments()}
     */
    @SuppressWarnings(value = "unused")
    private Class[] getParameterizedClassTypeArguments(Type type0) {
        if (type0 instanceof ParameterizedType) {
            //泛型声明开始
            int begin = type0.toString().indexOf('<') + 1;
            //泛型声明结束
            int end = type0.toString().lastIndexOf('>');
            try {
                //截取泛型部分
                String classPathStr = type0.toString().substring(begin, end);
                //获得全部泛型
                // 注意有空格
                String[] allClassPathStr = classPathStr.split(", ");
                //创建类类型数组
                Class[] classes = new Class[allClassPathStr.length];
                for (int i = 0, len = allClassPathStr.length; i < len; i++) {
                    classes[i] = Class.forName(allClassPathStr[i]);
                }
                return classes;
            } catch (Exception e) {
                logger.error(e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * 执行SQL语句,且SQL语句是{@code INSERT}、{@code UPDATE}、{@code DELETE}或不返回任何内容的SQL语句,例如DDL语句
     *
     * @param connection 数据库连接对象
     * @param sql        SQL语句
     * @param args       sql语句对应参数
     * @return (1)SQL数据操作语言 (DML)语句的行数
     * (2) 0 用于不返回任何内容的SQL语句
     * @since 1.0
     */
    public int update(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            this.setParameter(preparedStatement, args);
            //返回影响的行数
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            //释放资源-----连接不能关,由创建Connection的地方关闭
            JDBCUtils.close(null, preparedStatement, null);
        }
        return 0;
    }

    /**
     * 获取javaBean对象
     *
     * @param connection 数据库连接对象
     * @param sql        SQL语句
     * @param args       SQL语句参数
     * @return 如果数据库存在该对象则返回该对象，反之返回null
     * @since 1.0
     */
    public T getBean(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            this.setParameter(preparedStatement, args);
            //执行sql查询
            resultSet = preparedStatement.executeQuery();
            return this.getInstance(resultSet);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            //释放资源-----连接不能关,由创建Connection的地方关闭
            JDBCUtils.close(null, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * 获取数据库一组对象集合
     *
     * @param connection 数据库连接对象
     * @param sql        SQL语句
     * @param args       SQL语句参数
     * @return 如果数据库存在该对象集合则返回该对象集合，反之返回null
     * 异常返回null
     * @since 1.0
     */
    public List<T> getListBean(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            this.setParameter(preparedStatement, args);
            //执行sql查询
            resultSet = preparedStatement.executeQuery();
            T t;
            ArrayList<T> arrayList = new ArrayList<>();
            while ((t = this.getInstance(resultSet)) != null) {
                arrayList.add(t);
            }
            return arrayList;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            //释放资源-----连接不能关,由创建Connection的地方关闭
            JDBCUtils.close(null, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * 获得一行一列的值
     *
     * @param connection 数据库连接对象
     * @param sql        SQL语句
     * @param args       SQL语句参数
     * @return 如果数据库有该值则返回该值的对象，反之返回null
     * @since 1.0
     */
    public Object getSingleValue(Connection connection, String sql, Object... args) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            this.setParameter(preparedStatement, args);
            //执行sql查询
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getObject(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            JDBCUtils.close(null, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * 填充占位符
     *
     * @param preparedStatement 数据库操作对象
     * @param args              sql语句参数
     * @since 1.0
     */
    private void setParameter(PreparedStatement preparedStatement, Object... args) {
        try {
            for (int i = 0, length = args.length; i < length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 获取类型{@code type}实例,并用{@code resultSet}获取到的值初始化变量
     *
     * @param resultSet 结果集
     * @return 结果集有对应值则返回初始化的对象，反之返回null
     * @since 1.0
     */
    private T getInstance(ResultSet resultSet) {
        try {
            //获取结果集元数据
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取列数
            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.next()) {
                //创建空的javaBean对象
                Constructor<T> declaredConstructor = type.getDeclaredConstructor();
                T t = declaredConstructor.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object object = resultSet.getObject(i + 1);
                    //获取as列名
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
                    //初始化字段
                    Field field = type.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, object);
                }
                return t;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}

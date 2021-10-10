package team.skylinekids.commonweal.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类,该类可以获取和释放连接
 * 结合druid数据库连接池
 *
 * @author MysticalDream
 * @since 2.0
 */
public final class JDBCUtils {
    /**
     * 数据库连接池对象
     */
    public static DruidDataSource dataSource;
    /**
     * 在当前线程保留Connection对象
     */
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 工具类不需要创建对象
     */
    private JDBCUtils() {

    }

    //读取jdbc.properties,并创建数据库连接池
    static {
        //创建properties 读取配置文件
        Properties properties = new Properties();
        //读取jdbc.properties属性配置文件
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            //从流中加载数据
            properties.load(is);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池的连接
     *
     * @return 如果返回null，说明获取连接失败,反之则成功获取
     */
    public static Connection getConnection() {
        //获得当前线程中的Connection对象
        Connection connection = threadLocal.get();
        //没有则重新获取一个Connection对象
        if (connection == null) {
            //获取数据库连接
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //将获取到的Connection对象加入当前线程
            threadLocal.set(connection);
        }
        return connection;
    }

    /**
     * 开启事务
     *
     * @throws SQLException
     */
    public static void beginTransaction() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    /**
     * 关闭事务，关闭连接
     */
    public static void closeTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            close(connection, null, null);
        }
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.commit();
        }
    }

    /**
     * 事务回滚
     *
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.rollback();
        }
    }

    /**
     * 释放资源
     *
     * @param connection 数据库连接对象
     * @param statement  数据库操作对象
     * @param resultSet  结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        //释放此Connection对象的数据库和JDBC资源
        if (connection != null) {
            try {
                connection.close();
                //从线程保存中移除
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //释放此Statement对象的数据库和JDBC 资源
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //释放此ResultSet对象的数据库和JDBC资源
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package team.skylinekids.commonweal.service.transaction;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 事务控制动态代理（代理模式）
 *
 * @author MysticalDream
 */
public class ServiceTransactionProxy implements InvocationHandler {

    private Logger logger = Logger.getLogger(ServiceTransactionProxy.class);

    private final Object serviceObject;

    public ServiceTransactionProxy(Object serviceObject) {
        this.serviceObject = serviceObject;
    }

    /**
     * 构造方法私有化，提供静态方法创建对象
     *
     * @param proxiedObject 要代理的事物对象
     * @param <T>           代理对象类型
     * @return 代理对象
     */
    public static <T> ServiceTransactionProxy create(T proxiedObject) {
        return new ServiceTransactionProxy(proxiedObject);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object value;
        try {
            //开启事务
            JDBCUtils.beginTransaction();
            //调用方法
            value = method.invoke(serviceObject, args);
        } catch (Exception e) {
            logger.error("Service出现异常了！事务回滚");
            JDBCUtils.rollbackTransaction();
            throw e;
        } finally {
            //提交事务
            JDBCUtils.commitTransaction();
            //关闭连接
            JDBCUtils.closeTransaction();
        }
        return value;
    }
}

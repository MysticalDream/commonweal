package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.service.transaction.ServiceTransactionProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @author MysticalDream
 */
public class ProxyFactory {
    /**
     * 获取代理后的对象
     *
     * @param clazz        类对象
     * @param proxyWrapper 包裹执行对象的实现接口
     * @param <T>          泛型
     * @return 创建一个被代理后的对象
     */
    private static <T> T getInstance(Class<T> clazz, InvocationHandler proxyWrapper) {
        //生成动态代理对象
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                proxyWrapper
        );
    }

    /**
     * 获取事务的代理对象
     *
     * @param proxiedObject 要代理的对象
     * @param <T>
     * @return 被代理后的对象
     */
    public static <T> T getTransactionProxy(T proxiedObject) {
        // 传入要代理的对象，获取被代理后的对象
        return (T) getInstance(proxiedObject.getClass(),
                ServiceTransactionProxy.create(proxiedObject));
    }
}

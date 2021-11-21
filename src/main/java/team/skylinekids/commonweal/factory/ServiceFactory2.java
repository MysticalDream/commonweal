package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.factory.exception.ServiceBeanException;

/**
 * 服务工厂
 *
 * @author MysticalDream
 */
public final class ServiceFactory2 {
    private final static String PATH = "serviceConfig.properties";

    private ServiceFactory2() {

    }

    /**
     * 获取被代理的service
     *
     * @param tClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getServiceImplProxy(Class<T> tClass) {
        Object beanPro = BeanFactory.getBeanPro(tClass.getSimpleName(), PATH);
        if (tClass.isInstance(beanPro)) {
            return (T) ProxyFactory.getTransactionProxy(beanPro);
        }
        throw new ServiceBeanException("获取service异常");
    }

}

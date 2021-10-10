package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.service.impl.UserServiceImpl;

/**
 * 获取Service对象的代理工厂
 *
 * @author MysticalDream
 */
public class ServiceFactory {

    public static UserService getUserService() {
        return ProxyFactory.getTransactionProxy(new UserServiceImpl());
    }
}

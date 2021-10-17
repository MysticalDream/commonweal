package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.service.ItemService;
import team.skylinekids.commonweal.service.ProvinceService;
import team.skylinekids.commonweal.service.TeamService;
import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.service.impl.ItemServiceImpl;
import team.skylinekids.commonweal.service.impl.ProvinceServiceImpl;
import team.skylinekids.commonweal.service.impl.TeamServiceImpl;
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

    public static ItemService getItemService() {
        return ProxyFactory.getTransactionProxy(new ItemServiceImpl());
    }

    public static TeamService getTeamService() {
        return ProxyFactory.getTransactionProxy(new TeamServiceImpl());
    }

    public static ProvinceService getProvinceService() {
        return ProxyFactory.getTransactionProxy(new ProvinceServiceImpl());
    }

}

package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.service.*;
import team.skylinekids.commonweal.service.impl.*;

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

    public static ItemMemberMapService getItemMemberMapService() {
        return ProxyFactory.getTransactionProxy(new ItemMemberMapServiceImpl());
    }

    public static ItemBOService getItemBOService() {
        return ProxyFactory.getTransactionProxy(new ItemBOServiceImpl());
    }

    public static ThumbService getThumbService() {
        return new ThumbServiceImpl();
    }

}

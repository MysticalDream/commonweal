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

    public static TeamBOService getTeamBOService() {
        return ProxyFactory.getTransactionProxy(new TeamBOServiceImpl());
    }

    public static ThumbService getThumbService() {
        return ProxyFactory.getTransactionProxy(new ThumbServiceImpl());
    }

    public static RecruitService getRecruitService() {
        return ProxyFactory.getTransactionProxy(new RecruitServiceImpl());
    }

    public static TeamMemberMapService getTeamMemberMapService() {
        return ProxyFactory.getTransactionProxy(new TeamMemberMapServiceImpl());
    }

    public static AchievementService getAchievementService() {
        return ProxyFactory.getTransactionProxy(new AchievementServiceImpl());
    }

    public static FarmerInfoService getFarmerInfoService() {
        return ProxyFactory.getTransactionProxy(new FarmerInfoServiceImpl());
    }

    public static AdoptService getAdoptService() {
        return ProxyFactory.getTransactionProxy(new AdoptServiceImpl());
    }

    public static LiveService getLiveService() {
        return ProxyFactory.getTransactionProxy(new LiveServiceImpl());
    }

}

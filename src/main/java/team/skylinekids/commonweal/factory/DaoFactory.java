package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.dao.*;
import team.skylinekids.commonweal.dao.impl.*;


/**
 * Dao工厂
 * 通过工厂来获取Dao即可
 * 不用自己手动一个一个地方的去创建对应的对象
 *
 * @author MysticalDream
 */
public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static ItemDao getItemDao() {
        return new ItemDaoImpl();
    }

    public static TeamDao getTeamDao() {
        return new TeamDaoImpl();
    }

    public static ProvinceDao getProvinceDao() {
        return new ProvinceDaoImpl();
    }

    public static ItemMemberMapDao getItemMemberMapDao() {
        return new ItemMemberMapDaoImpl();
    }

    public static ItemAchievementDao getItemAchievementDao() {
        return new ItemAchievementDaoImpl();
    }

    public static TeamAchievementDao getTeamAchievementDao() {
        return new TeamAchievementDaoImpl();
    }

    public static ThumbDao getThumbDao() {
        return new ThumbDaoImpl();
    }

    public static RecruitDao getRecruitDao() {
        return new RecruitDaoImpl();
    }

    public static TeamMemberMapDao getTeamMemberMapDao() {
        return new TeamMemberMapDaoImpl();
    }

    public static AchievementDao getAchievementDao() {
        return new AchievementDaoImpl();
    }

    public static FarmerInfoDao getFarmerInfoDao() {
        return new FarmerInfoDaoImpl();
    }

    public static AdoptDao getAdoptDao() {
        return new AdoptDaoImpl();
    }
}

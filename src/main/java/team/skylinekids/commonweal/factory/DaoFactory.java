package team.skylinekids.commonweal.factory;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.dao.impl.ItemDaoImpl;
import team.skylinekids.commonweal.dao.impl.TeamDaoImpl;
import team.skylinekids.commonweal.dao.impl.UserDaoImpl;


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

}

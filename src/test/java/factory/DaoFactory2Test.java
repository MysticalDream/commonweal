package factory;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.dao.impl.UserDaoImpl;
import team.skylinekids.commonweal.factory.DaoFactory2;
import team.skylinekids.commonweal.pojo.po.User;

import static org.junit.jupiter.api.Assertions.*;

class DaoFactory2Test {

    @Test
    void getInstance() throws Exception {
        UserDao daoImpl = DaoFactory2.getDaoImpl(UserDao.class);
        User user = new User();
        user.setUserId(1);
        System.out.println(daoImpl.getUser(user));
    }

    @Test
    void getDaoImpl() {
        System.out.println(UserDao.class.isInstance(new UserDaoImpl()));
    }

    @Test
    void testGetDaoImpl() {
    }

    @Test
    void testGetDaoImpl1() {
    }
}
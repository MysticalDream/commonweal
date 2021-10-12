package dao;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.dao.impl.UserDaoImpl;
import team.skylinekids.commonweal.pojo.po.User;

class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    void addUser() throws Exception {
        User user = new User();
        user.setPassword("123242");
        user.setUsername("000");
        user.setTel("0123019203");
        int i = userDao.addUser(user);
        System.out.println(i);
    }

    @Test
    void getUserByUserName() throws Exception {
        User userByUserName = userDao.getUserByUserName("000");
        System.out.println(userByUserName);
    }

    @Test
    void getUserById() throws Exception {
        User user = userDao.getUserById(2);
    }

    @Test
    void updateUser() throws Exception {
        User user = new User();
        user.setUserId(2);
        user.setPassword("00390903");
        user.setAvailable(null);
        userDao.updateUser(user);
    }

    @Test
    void removeUser() {

    }

    @Test
    void removeUserById() throws Exception {
        int i = userDao.removeUserById(2);
        System.out.println(i);
    }

    @Test
    void updateUserAvatar() {
    }

    @Test
    void updatePassword() {
    }
}
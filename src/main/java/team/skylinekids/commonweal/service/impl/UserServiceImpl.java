package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.utils.ConversionUtils;

/**
 * @author MysticalDream
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = DaoFactory.getUserDao();

    @Override
    public int register(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User login(String userName, String password) {
        User user = userDao.getUserByUserName(userName);
        if (user == null) {
            return null;
        }
        return user.getPassword().equals(password) ? user : null;
    }

    @Override
    public User login(User user) {
        User user1 = userDao.getUser(user);
        return user1;
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.getUserById(id);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        return user;
    }

    @Override
    public boolean isUserNameExisted(String name) {
        return getUserByUsername(name) == null ? false : true;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);

    }

    @Override
    public int updateUserPassword(Integer id, String password) {
        return userDao.updatePassword(id, password);
    }

    @Override
    public int deleteUser(User user) {
        return userDao.removeUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userDao.removeUserById(id);
    }
}

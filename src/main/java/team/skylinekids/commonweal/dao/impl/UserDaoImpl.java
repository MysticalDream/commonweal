package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.dao.core.GenericBaseMapper;
import team.skylinekids.commonweal.pojo.po.User;

/**
 * @author MysticalDream
 */
public class UserDaoImpl extends GenericBaseMapper<User> implements UserDao {

    @Override
    public int addUser(User user) {
        Integer insert = this.insert(user);
        return insert.intValue();
    }

    @Override
    public User getUser(User user) {
        User selectOne = this.selectOne(user);
        return selectOne;
    }

    @Override
    public User getUserByUserName(String name) {
        User user = new User();
        user.setUsername(name);
        return this.selectOne(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setUserId(id);
        return this.selectOne(user);
    }

    @Override
    public int updateUser(User user) {
        Integer update = this.update(user);
        return update.intValue();
    }

    @Override
    public int removeUser(User user) {
        return removeUserById(user.getUserId());
    }

    @Override
    public int removeUserById(Integer id) {
        User user = new User();
        user.setUserId(id);
        user.setAvailable(false);
        return this.update(user).intValue();
    }

    @Override
    public int updateUserAvatar(Integer id, String newAvatar) {
        User user = new User();
        user.setUserId(id);
        user.setAvatarUrl(newAvatar);
        return this.update(user).intValue();
    }

    @Override
    public int updatePassword(Integer id, String password) {
        User user = new User();
        user.setUserId(id);
        user.setPassword(password);
        return this.update(user).intValue();
    }
}

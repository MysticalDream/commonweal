package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.dao.core.GenericBaseMapper;
import team.skylinekids.commonweal.pojo.po.User;

/**
 * @author MysticalDream
 */
public class UserDaoImpl extends GenericBaseMapper<User> implements UserDao {

    @Override
    public int addUser(User user) throws Exception {
        Integer insert = this.insert(user);
        return insert.intValue();
    }

    @Override
    public User getUser(User user) throws Exception {
        User selectOne = this.selectOne(user);
        return selectOne;
    }

    @Override
    public User getUserByUserName(String name) throws Exception {
        User user = new User();
        user.setUsername(name);
        return this.selectOne(user);
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        User user = new User();
        user.setUserId(id);
        return this.selectOne(user);
    }

    @Override
    public int updateUser(User user) throws Exception {
        Integer update = this.update(user);
        return update.intValue();
    }

    @Override
    public int removeUser(User user) throws Exception {
        return removeUserById(user.getUserId());
    }

    @Override
    public int removeUserById(Integer id) throws Exception {
        User user = new User();
        user.setUserId(id);
        user.setAvailable(false);
        return this.update(user).intValue();
    }

    @Override
    public int updateUserAvatar(Integer id, String newAvatar) throws Exception {
        User user = new User();
        user.setUserId(id);
        user.setAvatarUrl(newAvatar);
        return this.update(user).intValue();
    }

    @Override
    public int updatePassword(Integer id, String password) throws Exception {
        User user = new User();
        user.setUserId(id);
        user.setPassword(password);
        return this.update(user).intValue();
    }
}

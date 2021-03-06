package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.utils.JDBCUtils;
import team.skylinekids.commonweal.utils.SqlUtils;
import team.skylinekids.commonweal.utils.reflect.ReflectUtils;

import java.util.List;

/**
 * @author MysticalDream
 */
public class UserDaoImpl extends MyGenericBaseDao<User> implements UserDao {

    private final Logger logger = Logger.getLogger(UserDaoImpl.class);

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

    @Override
    public List<User> getItemUserList(Integer itemId) throws Exception {
        String sql = "SELECT " + SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(User.class), true) + " FROM " + this.getTableName() + " WHERE user_id IN (SELECT target_id FROM item_and_member_map WHERE item_id=? AND type=? AND is_available=? AND status=?)";
        logger.info("===>   Preparing:" + sql);
        logger.info("===>   Parameters:" + "[" + itemId + ",true,true,1]");
        List<User> users = this.getListBean(JDBCUtils.getConnection(), sql, itemId, true, true, 1);
        return users;
    }

    @Override
    public List<User> getTeamUserList(Integer teamId) throws Exception {
        String sql = "SELECT " + SqlUtils.getSelectColumnsByField(ReflectUtils.getAllFields(User.class), true) + " FROM " + this.getTableName() + " WHERE user_id IN (SELECT user_id FROM team_and_user_map WHERE team_id=? AND is_available=? AND status=?)";
        logger.info("===>   Preparing:" + sql);
        logger.info("===>   Parameters:" + "[" + teamId + ",true,1]");
        List<User> users = this.getListBean(JDBCUtils.getConnection(), sql, teamId, true, 1);
        return users;
    }

}

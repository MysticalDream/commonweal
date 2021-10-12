package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.User;

import java.sql.SQLException;

/**
 * @author MysticalDream
 */
public interface UserDao {
    /**
     * 添加新的用户
     *
     * @param user 新用户对象
     * @return 返回0说明添加失败，反之成功
     */
    int addUser(User user) throws Exception;

    /**
     * 根据user条件获取用户信息
     *
     * @param user 用户对象条件
     * @return 如果存在该用户则返回该用户对象，反之返回null
     */
    User getUser(User user) throws Exception;

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return 如果存在该用户则返回该用户对象，反之返回null
     */
    User getUserByUserName(String name) throws Exception;

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 如果存在该用户则返回该用户对象，反之返回null
     */
    User getUserById(Integer id) throws Exception;

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return 返回0说明更新失败，反之成功
     */
    int updateUser(User user) throws Exception;

    /**
     * 删除用户
     *
     * @param user 用户对象
     * @return 返回0说明删除失败，反之成功
     */
    int removeUser(User user) throws Exception;

    /**
     * 通过id删除用户
     *
     * @param id 用户id
     * @return 返回0说明删除失败，反之成功
     */
    int removeUserById(Integer id) throws Exception;

    /**
     * 更新用户头像
     *
     * @param id        用户id
     * @param newAvatar 用户新头像
     * @return 返回0说明更新失败，反之成功
     */
    int updateUserAvatar(Integer id, String newAvatar) throws Exception;

    /**
     * 修改密码
     *
     * @param id       用户id
     * @param password 密码字符串
     * @return 返回0说明修改失败，反之成功
     */
    int updatePassword(Integer id, String password) throws Exception;
}

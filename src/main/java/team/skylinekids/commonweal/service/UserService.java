package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.User;

/**
 * 用户服务
 *
 * @author MysticalDream
 */
public interface UserService {
    /**
     * 注册用户
     *
     * @param user 注册的用户对象
     * @return 成功返回 1 说明添加成功,反之返回 0 失败
     */
    int register(User user);

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回该用户信息，反之返回null
     */
    User login(String userName, String password);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 如果用户存在返回该用户, 否则返回null
     */
    User getUserById(Integer id);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 如果用户存在返回该用户, 否则返回null
     */
    User getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     *
     * @param name 用户名
     * @return 如果用户名存在返回true, 否则返回false
     */
    boolean isUserNameExisted(String name);

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return 成功返回 1 说明更新成功,反之返回 0 失败
     */
    int updateUser(User user);

    /**
     * 更新用户密码
     *
     * @param id       用户id
     * @param password 密码
     * @return 成功返回 1 说明更新成功,反之返回 0 失败
     */
    int updateUserPassword(Integer id, String password);

    /**
     * 删除注销用户
     *
     * @param user 用户对像
     * @return 成功返回 1 说明删除成功,反之返回 0 失败
     */
    int deleteUser(User user);

    /**
     * 根据id删除注销用户
     *
     * @param id
     * @return
     */
    int deleteUserById(Integer id);
}

package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.UserInfo;

/**
 * 用户信息
 *
 * @author MysticalDream
 */
public interface UserInfoDao {
    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    int addUserInfo(UserInfo userInfo) throws Exception;
}

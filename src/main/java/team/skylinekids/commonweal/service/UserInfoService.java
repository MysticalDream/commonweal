package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.UserInfo;

/**
 * 用户信息
 *
 * @author MysticalDream
 */
public interface UserInfoService {
    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    int addUserInfo(UserInfo userInfo) throws Exception;


}

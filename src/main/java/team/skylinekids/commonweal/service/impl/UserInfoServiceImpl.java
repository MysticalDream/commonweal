package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.UserInfoDao;
import team.skylinekids.commonweal.factory.DaoFactory2;
import team.skylinekids.commonweal.pojo.po.UserInfo;
import team.skylinekids.commonweal.service.UserInfoService;

/**
 * @author MysticalDream
 */
public class UserInfoServiceImpl implements UserInfoService {

    UserInfoDao userInfoDao = DaoFactory2.getDaoImpl(UserInfoDao.class);
    @Override
    public int addUserInfo(UserInfo userInfo) throws Exception {
        return userInfoDao.addUserInfo(userInfo);
    }
}

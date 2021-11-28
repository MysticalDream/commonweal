package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.UserInfoDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.UserInfo;

/**
 * @author MysticalDream
 */
public class UserInfoDaoImpl extends MyGenericBaseDao<UserInfo> implements UserInfoDao {
    @Override
    public int addUserInfo(UserInfo userInfo) throws Exception {
        UserInfo userInfoCondition = new UserInfo();
        userInfoCondition.setUserId(userInfo.getUserId());
        UserInfo userInfo1 = this.selectOne(userInfoCondition);
        if (userInfo1 == null) {
            return this.insert(userInfo);
        } else {
            userInfo.setUserInfoId(userInfo1.getUserInfoId());
            return this.update(userInfo);
        }
    }
}

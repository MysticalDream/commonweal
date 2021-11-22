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
        return this.insert(userInfo);
    }
}

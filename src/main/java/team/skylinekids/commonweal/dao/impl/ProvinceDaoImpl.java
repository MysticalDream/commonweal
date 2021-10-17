package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.ProvinceDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.Province;

/**
 * @author MysticalDream
 */
public class ProvinceDaoImpl extends MyGenericBaseDao<Province> implements ProvinceDao {
    @Override
    public String getCityNameByCondition(Province province) throws Exception {
        Province province1 = this.selectOne(province);
        return province1.getName();
    }
}

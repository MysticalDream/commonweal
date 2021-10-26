package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.ProvinceDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.utils.StringUtils;

/**
 * @author MysticalDream
 */
public class ProvinceDaoImpl extends MyGenericBaseDao<Province> implements ProvinceDao {
    @Override
    public String getCityNameByCondition(Province province) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(10);
        Province province1 = new Province();
        if (StringUtils.isNotBlank(province.getProvince())) {
            province1.setProvince(province.getProvince());
            stringBuilder.append(this.selectOne(province1).getName());
        }
        if (StringUtils.isNotBlank(province.getCity())) {
            province1.setCity(province.getCity());
            stringBuilder.append(this.selectOne(province1).getName());
        }
        if (StringUtils.isNotBlank(province.getArea())) {
            province1.setArea(province.getArea());
            stringBuilder.append(this.selectOne(province1).getName());
        }
        if (StringUtils.isNotBlank(province.getTown())) {
            province1.setTown(province.getTown());
            stringBuilder.append(this.selectOne(province1).getName());
        }
        return stringBuilder.toString();
    }
}

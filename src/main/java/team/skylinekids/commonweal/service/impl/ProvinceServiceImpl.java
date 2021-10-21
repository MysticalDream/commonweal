package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ProvinceDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.service.ProvinceService;

/**
 * @author MysticalDream
 */
public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDao provinceDao = DaoFactory.getProvinceDao();

    @Override
    public String getCityNameByCondition(Province province) throws Exception {
        return provinceDao.getCityNameByCondition(province);
    }
}

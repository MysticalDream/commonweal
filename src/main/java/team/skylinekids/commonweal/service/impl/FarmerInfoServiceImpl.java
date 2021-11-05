package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.FarmerInfoDao;
import team.skylinekids.commonweal.dao.ProvinceDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;
import team.skylinekids.commonweal.service.FarmerInfoService;

/**
 * 助农信息
 *
 * @author MysticalDream
 */
public class FarmerInfoServiceImpl implements FarmerInfoService {

    FarmerInfoDao farmerInfoDao = DaoFactory.getFarmerInfoDao();
    ProvinceDao provinceDao = DaoFactory.getProvinceDao();

    @Override
    public Page<FarmerInfo> getFarmerInfoList(FarmerInfoCondition farmerInfoCondition) throws Exception {
        Page<FarmerInfo> farmerInfoList = farmerInfoDao.getFarmerInfoList(farmerInfoCondition);
        Province province = new Province();
        for (FarmerInfo farmerInfo : farmerInfoList.getList()) {
            province.setProvince(farmerInfo.getProvince());
            province.setCity(farmerInfo.getCity());
            province.setArea(farmerInfo.getArea());
            province.setTown(farmerInfo.getTown());
            farmerInfo.setLocation(provinceDao.getCityNameByCondition(province));
        }
        return farmerInfoList;
    }

    @Override
    public int addFarmerInfo(FarmerInfo farmerInfo) throws Exception {
        return farmerInfoDao.addFarmerInfo(farmerInfo);
    }
}

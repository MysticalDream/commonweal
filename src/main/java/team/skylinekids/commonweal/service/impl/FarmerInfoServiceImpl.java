package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.FarmerInfoDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;
import team.skylinekids.commonweal.service.FarmerInfoService;

/**
 * 助农信息
 *
 * @author MysticalDream
 */
public class FarmerInfoServiceImpl implements FarmerInfoService {

    FarmerInfoDao farmerInfoDao = DaoFactory.getFarmerInfoDao();

    @Override
    public Page<FarmerInfo> getFarmerInfoList(FarmerInfoCondition farmerInfoCondition) throws Exception {
        return farmerInfoDao.getFarmerInfoList(farmerInfoCondition);
    }

    @Override
    public int addFarmerInfo(FarmerInfo farmerInfo) throws Exception {
        return farmerInfoDao.addFarmerInfo(farmerInfo);
    }
}

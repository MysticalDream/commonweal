package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;

/**
 * 助农信息Dao
 *
 * @author MysticalDream
 */
public interface FarmerInfoDao {
    /**
     * 获取助农列表
     *
     * @param farmerInfoCondition
     * @return
     * @throws Exception
     */
    Page<FarmerInfo> getFarmerInfoList(FarmerInfoCondition farmerInfoCondition) throws Exception;

    /**
     * 添加助农信息
     *
     * @param farmerInfo
     * @return
     * @throws Exception
     */
    int addFarmerInfo(FarmerInfo farmerInfo) throws Exception;
}

package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;

/**
 * 助农服务
 *
 * @author MysticalDream
 */
public interface FarmerInfoService {
    /**
     * 获取助农信息
     *
     * @param farmerInfoCondition
     * @return
     * @throws Exception
     */
    Page<FarmerInfo> getFarmerInfoList(FarmerInfoCondition farmerInfoCondition) throws Exception;
}

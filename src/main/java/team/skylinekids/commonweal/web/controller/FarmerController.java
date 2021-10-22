package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FarmerInfo;
import team.skylinekids.commonweal.pojo.query.FarmerInfoCondition;
import team.skylinekids.commonweal.service.FarmerInfoService;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.io.IOException;

/**
 * 助农信息展示
 *
 * @author MysticalDream
 */
public class FarmerController {
    private final Logger logger = Logger.getLogger(FarmerController.class);
    FarmerInfoService farmerInfoService = ServiceFactory.getFarmerInfoService();

    @MyRequestPath(value = "/agriculture", type = {RequestMethod.GET})
    public String getFarmerInfoList(HttpInfoWrapper httpInfoWrapper) throws Exception {
        FarmerInfoCondition farmerInfoCondition = GsonUtils.j2O(httpInfoWrapper.getJsonString(), FarmerInfoCondition.class);
        Page<FarmerInfo> farmerInfoList = farmerInfoService.getFarmerInfoList(farmerInfoCondition);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, farmerInfoList);
    }
}

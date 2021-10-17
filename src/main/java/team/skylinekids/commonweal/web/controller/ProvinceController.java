package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.service.ProvinceService;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;


/**
 * 省份查询接口
 *
 * @author MysticalDream
 */
public class ProvinceController {

    ProvinceService provinceService = ServiceFactory.getProvinceService();

    /**
     * 查询全国省市县行政区
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/area/province", type = {RequestMethod.GET})
    public String getName(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String jsonString = httpInfoWrapper.getJsonString();
        Province provinceCondition = GsonUtils.j2O(jsonString, Province.class);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, provinceService.getCityNameByCondition(provinceCondition));
    }
}

package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.pojo.po.WallCloud;
import team.skylinekids.commonweal.service.WallCloudService;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 云墙
 *
 * @author MysticalDream
 */
public class WallCloudController {

    WallCloudService wallCloudService = ServiceFactory2.getServiceImplProxy(WallCloudService.class);

    @MyRequestPath(value = "/wall", type = RequestMethod.POST)
    @AccessLevel
    public String addWallCloud(HttpInfoWrapper httpInfoWrapper) throws Exception {
        User user = httpInfoWrapper.getUser();
        WallCloud wallCloud = GsonUtils.j2O(httpInfoWrapper.getJsonString(), WallCloud.class);
        wallCloud.setUserId(user.getUserId());
        wallCloudService.addWallCloud(wallCloud);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, wallCloud);
    }

    @MyRequestPath(value = "/wall/list", type = RequestMethod.GET)
    public String getPagination(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Page<WallCloud> wallCloudPage = FillBeanUtils.fill(httpInfoWrapper.getParameterMap(), Page.class);
        System.out.println(wallCloudPage);
        wallCloudService.getPagination(wallCloudPage);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, wallCloudPage);
    }
}

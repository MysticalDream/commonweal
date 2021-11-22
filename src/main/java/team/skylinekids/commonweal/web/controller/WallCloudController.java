package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
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

    private Logger logger = Logger.getLogger(WallCloudController.class);

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
        Integer pageSize;
        Integer pageNum;
        String flag;
        try {
            pageSize = Integer.parseInt(httpInfoWrapper.getParameter("pageSize"));
            pageNum = Integer.parseInt(httpInfoWrapper.getParameter("pageNum"));
            flag = httpInfoWrapper.getParameter("flag");
            if ((!"true".equals(flag)) && (!"false".equals(flag))) {
                throw new Exception("标志不符合");
            }
        } catch (Exception e) {
            logger.error("领养请求分页语法错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<WallCloud> wallCloudPage = new Page<>();
        wallCloudPage.setPageNum(pageNum);
        wallCloudPage.setPageSize(pageSize);
        wallCloudService.getPagination(wallCloudPage, flag);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, wallCloudPage);
    }
}

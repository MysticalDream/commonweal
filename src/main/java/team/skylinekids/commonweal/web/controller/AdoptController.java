package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Adopt;
import team.skylinekids.commonweal.service.AdoptService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.util.Map;

/**
 * 领养动物模块
 *
 * @author MysticalDream
 */
public class AdoptController {

    private final Logger logger = Logger.getLogger(AdoptController.class);

    AdoptService adoptService = ServiceFactory.getAdoptService();

    /**
     * 封面上传接口
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/adopt/cover", type = {RequestMethod.POST})
    public String uploadAdoptCover(HttpInfoWrapper httpInfoWrapper) {

        return "未开发";
    }

    /**
     * 添加领养动物信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/adopt", type = {RequestMethod.POST})
    public String addAdoptInfo(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Adopt adopt = GsonUtils.j2O(httpInfoWrapper.getJsonString(), Adopt.class);
        String fileName = FileUtils.getFileName(adopt.getCoverUrl());
        //把封面从暂存区放到真正的目录中
        if (!FileUtils.cutFile(ResourcePathConstant.DISK_ADOPT_TEMP_IMG_BASE + fileName, ResourcePathConstant.DISk_ADOPT_IMG_BASE + fileName)) {
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
        adopt.setCoverUrl(fileName);
        adoptService.addAdoptInfo(adopt);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 获取领养动物的列表
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/adopts", type = {RequestMethod.GET})
    public String getAdoptList(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Map<String, Object> map = GsonUtils.jsonToMap(httpInfoWrapper.getParameter("json"));
        Integer pageSize;
        Integer pageNum;
        try {
            pageSize = Integer.parseInt((String) map.get("pageSize"));
            pageNum = Integer.parseInt((String) map.get("pageNum"));
        } catch (Exception e) {
            logger.error("领养请求分页语法错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<Adopt> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        Page<Adopt> adoptList = adoptService.getAdoptList(page);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, adoptList);
    }

}
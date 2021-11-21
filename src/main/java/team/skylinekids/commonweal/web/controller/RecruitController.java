package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.po.Recruit;
import team.skylinekids.commonweal.pojo.query.RecruitCondition;
import team.skylinekids.commonweal.service.RecruitService;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;

/**
 * 招募志愿者
 *
 * @author MysticalDream
 */
public class RecruitController {

    private Logger logger = Logger.getLogger(RecruitController.class);

    RecruitService recruitService = ServiceFactory.getRecruitService();

    /**
     * 条件获取志愿项目
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/recruits/conditions", type = {RequestMethod.GET})
    public String getRecruitsByConditionPage(HttpInfoWrapper httpInfoWrapper) throws Exception {
        RecruitCondition recruitCondition = FillBeanUtils.fill(httpInfoWrapper.getParameterMap(), RecruitCondition.class);
        recruitCondition.setRecruitCategoryId(CategoryUtils.getCategoryIdByName(recruitCondition.getRecruitCategory()));
        Page<RecruitDTO> page = recruitService.getRecruitByCondition(recruitCondition);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, page);
    }

    /**
     * 添加招募志愿者信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/recruits", type = {RequestMethod.POST})
    public String addRecruit(HttpInfoWrapper httpInfoWrapper) throws Exception {
        //招募志愿信息
        RecruitDTO recruitDTO = GsonUtils.j2O(httpInfoWrapper.getJsonString(), RecruitDTO.class);
        if (recruitDTO == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //文件名
        String fileName = FileUtils.getFileName(recruitDTO.getCoverUrl());
        //把封面从暂存区放到真正的目录中
        if (!FileUtils.cutFile(ResourcePathConstant.DISK_RECRUIT_COVER_TEMP_BASE_URL + fileName, ResourcePathConstant.DISK_RECRUIT_COVER_BASE_URL + fileName)) {
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
        Recruit recruit = ConversionUtils.convert(recruitDTO, Recruit.class);
        //分类
        recruit.setCategoryId(CategoryUtils.getCategoryIdByName(recruitDTO.getCategory()));
        //保存封面
        recruit.setCoverUrl(fileName);
        recruitService.addRecruit(recruit);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 上传招募志愿者封面
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/recruits/cover", type = {RequestMethod.POST})
    public String uploadRecruitImg(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("recruit_cover").get(0);
        if (coverPart == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            String fileName = FileUtils.saveResourceByPart(coverPart, ResourcePathConstant.DISK_RECRUIT_COVER_TEMP_BASE_URL);
            return ResultUtils.getResult(ApiResultCode.SUCCESS, ResourcePathConstant.VIRTUAL_RECRUIT_COVER_TEMP_BASE + fileName);
        } catch (Exception e) {
            logger.error("招募志愿者封面上传处理失败", e);
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
    }


}

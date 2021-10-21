package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.AchievementBO;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.po.ItemAchievement;
import team.skylinekids.commonweal.pojo.po.TeamAchievement;
import team.skylinekids.commonweal.service.AchievementService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;

/**
 * 成就
 *
 * @author MysticalDream
 */
public class AchievementController {

    private final Logger logger = Logger.getLogger(AchievementController.class);

    AchievementService achievementService = ServiceFactory.getAchievementService();

    /**
     * 获取成就 1--项目成就 2----团队成就
     *
     * @return
     */
    @MyRequestPath(value = "/achievements", type = {RequestMethod.GET})
    public String getAchievement(HttpInfoWrapper httpInfoWrapper) {

        return "获取项目成就";
    }

    /**
     * 分页获取成就
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/achievements/conditions", type = {RequestMethod.GET})
    public String getAchievementCondition(HttpInfoWrapper httpInfoWrapper) {
        long millis = System.currentTimeMillis();
        Integer pageSize;
        Integer pageNum;
        try {
            pageSize = httpInfoWrapper.getParameter("pageSize", Integer.class);
            pageNum = httpInfoWrapper.getParameter("pageNum", Integer.class);
        } catch (ClassCastException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        return "";
    }

    /**
     * 添加成就 1--项目成就 2----团队成就
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/achievements", type = {RequestMethod.POST})
    public String addAchievement(HttpInfoWrapper httpInfoWrapper) throws Exception {
        AchievementBO achievementBO = GsonUtils.j2O(httpInfoWrapper.getJsonString(), AchievementBO.class);
        if (achievementBO == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Integer type = achievementBO.getType();
        if (type == 1) {
            ItemAchievement itemAchievement = ConversionUtils.convert(achievementBO, ItemAchievement.class);
            itemAchievement.setItemId(achievementBO.getTypeId());
            String fileName = FileUtils.getFileName(achievementBO.getCoverUrl());
            FileUtils.cutFile(ResourcePathConstant.DISK_ACHIEVEMENT_TEMP_IMG_BASE + fileName, ResourcePathConstant.DISK_ACHIEVEMENT_IMG_BASE + fileName);
            itemAchievement.setCoverUrl(fileName);
            achievementService.addAchievement(1, itemAchievement);
            return ResultUtils.getResult(ApiResultCode.SUCCESS);
        } else if (type == 2) {
            TeamAchievement teamAchievement = ConversionUtils.convert(achievementBO, TeamAchievement.class);
            teamAchievement.setTeamId(achievementBO.getTypeId());
            String fileName = FileUtils.getFileName(achievementBO.getCoverUrl());
            FileUtils.cutFile(ResourcePathConstant.DISK_ACHIEVEMENT_TEMP_IMG_BASE + fileName, ResourcePathConstant.DISK_ACHIEVEMENT_IMG_BASE + fileName);
            teamAchievement.setCoverUrl(fileName);
            achievementService.addAchievement(2, teamAchievement);
            return ResultUtils.getResult(ApiResultCode.SUCCESS);
        }
        return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
    }

    /**
     * 上传封面
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/achievements/cover", type = {RequestMethod.POST})
    public String uploadAchievementCover(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("achievement_cover");
        if (coverPart == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            String fileName = FileUtils.saveResourceByPart(coverPart, ResourcePathConstant.DISK_ACHIEVEMENT_TEMP_IMG_BASE);
            return ResultUtils.getResult(ApiResultCode.SUCCESS, ResourcePathConstant.VIRTUAL_ACHIEVEMENT_TEMP_IMG_BASE + fileName);
        } catch (Exception e) {
            logger.error("成就封面上传处理失败", e);
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
    }

}

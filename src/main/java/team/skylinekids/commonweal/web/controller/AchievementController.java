package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.CommonConstant;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.pojo.vo.AchievementVO;
import team.skylinekids.commonweal.service.AchievementService;
import team.skylinekids.commonweal.utils.CacheUtils;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 成就
 *
 * @author MysticalDream
 */
public class AchievementController {

    private final Logger logger = Logger.getLogger(AchievementController.class);

    AchievementService achievementService = ServiceFactory.getAchievementService();

    /**
     * 获取对应项目或则队伍
     * 成就列表 1--项目成就 2----团队成就
     *
     * @return
     */
    @MyRequestPath(value = "/achievements", type = {RequestMethod.GET})
    public String getAchievement(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Achievement achievement = FillBeanUtils.fill(httpInfoWrapper.getParameterMap(), Achievement.class);
        List<Achievement> achievementList = achievementService.getAchievementList(achievement);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, achievementList);
    }

    /**
     * 分页获取成就
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/achievements/conditions")
    public String getAchievementCondition(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pageSize;
        Integer pageNum;
        try {
            pageSize = Integer.parseInt(httpInfoWrapper.getParameter("pageSize"));
            pageNum = Integer.parseInt(httpInfoWrapper.getParameter("pageNum"));
        } catch (Exception e) {
            logger.error("成就请求分页语法错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //登录则获取用户是否点赞信息
        if (httpInfoWrapper.isLogin()) {
            Page<AchievementVO> page = new Page<>();
            page.setPageSize(pageSize);
            page.setPageNum(pageNum);
            Page<AchievementVO> achievementByLimit = achievementService.getAchievementVOByLimit(page, httpInfoWrapper.getUser().getUserId());
            return ResultUtils.getResult(ApiResultCode.SUCCESS, achievementByLimit);
        }
        Page<AchievementVO> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        Page<AchievementVO> achievementVOPage = achievementService.getAchievementByLimit(page);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, achievementVOPage);

    }

    /**
     * 添加成就 1--项目成就 2----团队成就
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/achievements", type = {RequestMethod.POST})
    public String addAchievement(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Achievement achievement = GsonUtils.j2O(httpInfoWrapper.getJsonString(), Achievement.class);
        if (achievement == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        String fileName = FileUtils.getFileName(achievement.getCoverUrl());
        FileUtils.cutFile(ResourcePathConstant.DISK_ACHIEVEMENT_TEMP_IMG_BASE + fileName, ResourcePathConstant.DISK_ACHIEVEMENT_IMG_BASE + fileName);
        achievement.setCoverUrl(fileName);
        achievementService.addAchievement(achievement);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 上传封面
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/achievements/cover", type = {RequestMethod.POST})
    public String uploadAchievementCover(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("achievement_cover").get(0);
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

    /**
     * 点赞数前三的成就
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/achievement/top3", type = {RequestMethod.GET})
    public String getAchievementTopThree(HttpInfoWrapper httpInfoWrapper) throws Exception {
        List list = CacheUtils.get(CommonConstant.TOP3_KEY, List.class);
        if (list != null) {
            return ResultUtils.getResult(ApiResultCode.SUCCESS, list);
        }
        List<Achievement> topThree = achievementService.getAchievementTopThree();
        //30天更新一次
        CacheUtils.put(CommonConstant.TOP3_KEY, topThree, 30, TimeUnit.DAYS);

        //  CacheUtils.put(CommonConstant.TOP3_KEY, topThree, 60, TimeUnit.SECONDS);

        return ResultUtils.getResult(ApiResultCode.SUCCESS, topThree);
    }

}

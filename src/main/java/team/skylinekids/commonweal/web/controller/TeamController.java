package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.service.TeamService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 团队Controller
 *
 * @author MysticalDream
 */
public class TeamController {

    private final Logger logger = Logger.getLogger(TeamController.class);

    TeamService teamService = ServiceFactory.getTeamService();

    /**
     * 创建团队
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/teams", type = {RequestMethod.POST})
    public String createTeam(HttpInfoWrapper httpInfoWrapper) throws Exception {
        if (!httpInfoWrapper.isLogin()) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHENTICATED);
        }
        Team team = GsonUtils.j2O(httpInfoWrapper.getJsonString(), Team.class);
        if (team == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        String teamTempAvatarUrl = team.getTeamAvatar();
        String fileName = FileUtils.getFileName(teamTempAvatarUrl);
        //把队伍头像从暂存区放到真正的目录中
        if (!FileUtils.cutFile(ResourcePathConstant.DISK_TEAM_COVER_TEMP_BASE_URL + fileName, ResourcePathConstant.DISK_TEAM_COVER_BASEURL + fileName)) {
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
        //队伍头像
        team.setTeamAvatar(fileName);
        //队伍创建者用户id
        team.setUserId(httpInfoWrapper.getUser().getUserId());

        teamService.createTeam(team);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    @MyRequestPath(value = "/teams/cover", type = {RequestMethod.POST})
    public String uploadTeamAvatar(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("team_cover");
        if (coverPart == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            String fileName = FileUtils.saveResourceByPart(coverPart, ResourcePathConstant.DISK_TEAM_COVER_TEMP_BASE_URL);
            return ResultUtils.getResult(ApiResultCode.SUCCESS, ResourcePathConstant.VIRTUAL_TEAM_COVER_TEMP_BASE + fileName);
        } catch (Exception e) {
            logger.error("团队封面上传处理失败", e);
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
    }
}

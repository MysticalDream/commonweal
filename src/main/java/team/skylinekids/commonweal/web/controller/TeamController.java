package team.skylinekids.commonweal.web.controller;

import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.bo.TeamBO;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.po.TeamMemberMap;
import team.skylinekids.commonweal.pojo.query.TeamCondition;
import team.skylinekids.commonweal.service.TeamBOService;
import team.skylinekids.commonweal.service.TeamMemberMapService;
import team.skylinekids.commonweal.service.TeamService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import java.util.List;

/**
 * 团队Controller
 *
 * @author MysticalDream
 */
public class TeamController {

    private final Logger logger = Logger.getLogger(TeamController.class);

    TeamService teamService = ServiceFactory.getTeamService();

    TeamBOService teamBOService = ServiceFactory.getTeamBOService();

    TeamMemberMapService teamMemberMapService = ServiceFactory.getTeamMemberMapService();

    /**
     * 创建团队
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/teams", type = {RequestMethod.POST})
    public String createTeam(HttpInfoWrapper httpInfoWrapper) throws Exception {
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

    /**
     * 团队头像上传
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/teams/cover", type = {RequestMethod.POST})
    public String uploadTeamAvatar(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("team_cover").get(0);
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

    /**
     * 根据条件获取团队信息
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/teams/conditions", type = {RequestMethod.GET})
    public String getItemsByConditionPage(HttpInfoWrapper httpInfoWrapper) throws Exception {
        TeamCondition teamCondition = FillBeanUtils.fill(httpInfoWrapper.getParameterMap(), TeamCondition.class);
        Page<TeamDTO> teamByCondition = teamService.getTeamByCondition(teamCondition);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, teamByCondition);
    }

    /**
     * 根据团队id获取团队成员列表
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/teams/members/?", type = {RequestMethod.GET})
    public String getTeamMember(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pathVariable;
        try {
            pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (JsonSyntaxException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        TeamBO teamBO = teamBOService.getTeamBOByTeamId(pathVariable);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, teamBO);
    }

    /**
     * 获取用户创建的队伍
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel
    @MyRequestPath(value = "/teams/user", type = {RequestMethod.GET})
    public String getUserOfTeam(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer userId = httpInfoWrapper.getUser().getUserId();
        Page<TeamDTO> page = new Page<>();
        try {
            page.setPageNum(httpInfoWrapper.getParameter("pageNum", Integer.class));
            page.setPageSize(httpInfoWrapper.getParameter("pageSize", Integer.class));
        } catch (Exception e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<TeamDTO> teams = teamService.getTeamsByUserId(page, userId);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, teams);
    }

    /**
     * 获取用户加入的队伍
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/teams/joined")
    public String getUserJoinedTeam(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer userId = httpInfoWrapper.getUser().getUserId();
        Page<TeamDTO> page = new Page<>();
        try {
            page.setPageNum(httpInfoWrapper.getParameter("pageNum", Integer.class));
            page.setPageSize(httpInfoWrapper.getParameter("pageSize", Integer.class));
        } catch (Exception e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<TeamDTO> userJoinedTeam = teamService.getUserJoinedTeam(page, userId);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, userJoinedTeam);
    }

    /**
     * 加入团队
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel
    @MyRequestPath(value = "/teams/enter", type = {RequestMethod.POST})
    public String joinTeam(HttpInfoWrapper httpInfoWrapper) throws Exception {
        TeamMemberMap teamMemberMap = GsonUtils.j2O(httpInfoWrapper.getJsonString(), TeamMemberMap.class);
        if (teamMemberMap == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        teamMemberMap.setUserId(httpInfoWrapper.getUser().getUserId());
        teamMemberMapService.addMember(teamMemberMap);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }
}

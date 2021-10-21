package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.query.TeamCondition;

import java.util.List;

/**
 * 队伍Service
 *
 * @author MysticalDream
 */
public interface TeamService {
    /**
     * 创建项目
     *
     * @param team
     * @return
     */
    int createTeam(Team team) throws Exception;

    /**
     * 根据项目id获取项目信息
     *
     * @param id
     * @return
     */
    Team getTeamById(int id) throws Exception;

    /**
     * 更新项目
     *
     * @param team
     * @return
     */
    int updateTeam(Team team) throws Exception;

    /**
     * 团队查找搜索
     *
     * @param teamCondition
     * @return
     * @throws Exception
     */
    Page<TeamDTO> getTeamByCondition(TeamCondition teamCondition) throws Exception;

    /**
     * 获取用户创建的团队列表
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<TeamDTO> getTeamsByUserId(Integer userId) throws Exception;

    /**
     * 获取用户加入的队伍
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<TeamDTO> getUserJoinedTeam(Integer userId) throws Exception;


}

package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.query.TeamCondition;

import java.util.List;

/**
 * 队伍Dao
 *
 * @author MysticalDream
 */
public interface TeamDao {
    /**
     * 添加队伍
     *
     * @param team
     * @return
     * @throws Exception
     */
    int addTeam(Team team) throws Exception;

    /**
     * 通过队伍id获取队伍信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    Team getTeamById(int id) throws Exception;


    /**
     * 更新队伍信息
     *
     * @param team
     * @return
     * @throws Exception
     */
    int updateTeam(Team team) throws Exception;

    /**
     * 更新队伍人数
     *
     * @param teamId
     * @param number
     * @return
     * @throws Exception
     */
    int updateNowMen(Integer teamId, Integer number) throws Exception;

    /**
     * 获取项目团队成员列表
     *
     * @param itemId
     * @return
     * @throws Exception
     */
    List<Team> getItemTeamList(Integer itemId) throws Exception;

    /**
     * 条件搜索团队
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
    Page<TeamDTO> getTeamsByUserId(Page<TeamDTO> page, Integer userId) throws Exception;

    /**
     * 获取用户加入的队伍的id
     *
     * @param userId
     * @return
     * @throws Exception
     */
    Page<TeamDTO> getUserJoinedTeam(Page<TeamDTO> page, Integer userId) throws Exception;

}

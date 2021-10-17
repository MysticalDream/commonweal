package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.Team;

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
}

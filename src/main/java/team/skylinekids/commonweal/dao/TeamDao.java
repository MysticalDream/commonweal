package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.Team;

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

}

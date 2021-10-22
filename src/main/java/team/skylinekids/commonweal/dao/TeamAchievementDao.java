package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.TeamAchievement;

import java.util.List;

/**
 * 团队成就
 *
 * @author MysticalDream
 * @deprecated
 */
public interface TeamAchievementDao {
    /**
     * 添加团队成就
     *
     * @param teamAchievement
     * @return
     */
    int addTeamAchievement(TeamAchievement teamAchievement) throws Exception;

    /**
     * 更新项目成就
     *
     * @param teamAchievement
     * @return
     */
    int updateTeamAchievement(TeamAchievement teamAchievement) throws Exception;

    /**
     * 根据团队id获取团队成就列表
     *
     * @param id 项目id
     * @return
     */
    List<TeamAchievement> getTeamAchievementById(int id) throws Exception;

    /**
     * 根据所给状态更新点赞数
     *
     * @param status
     * @param teamAchievementId
     * @return
     * @throws Exception
     */
    int updateLoveNumber(Integer teamAchievementId, boolean status) throws Exception;
}

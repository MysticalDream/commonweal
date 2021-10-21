package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.Achievement;

import java.util.List;

/**
 * @author MysticalDream
 */
public interface AchievementDao {
    /**
     * 添加成就
     *
     * @param achievement
     * @return
     */
    int addAchievement(Achievement achievement) throws Exception;

    /**
     * 更新成就
     *
     * @param achievement
     * @return
     */
    int updateAchievement(Achievement achievement) throws Exception;

    /**
     * 根据typeId和type条件获取成就列表
     *
     * @param achievement
     * @return
     */
    List<Achievement> getAchievementById(Achievement achievement) throws Exception;

    /**
     * 根据所给状态更新点赞数
     *
     * @param status
     * @param achievement
     * @return
     * @throws Exception
     */
    int updateLoveNumber(Achievement achievement, boolean status) throws Exception;
}

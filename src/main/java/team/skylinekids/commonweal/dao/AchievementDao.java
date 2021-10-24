package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
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
     * 根据achievement条件获取成就列表
     *
     * @param achievement
     * @return
     * @throws Exception
     */
    List<Achievement> getAchievementList(Achievement achievement) throws Exception;

    /**
     * 分页获取成就
     *
     * @param page
     * @param sort 为true是升序 false为降序
     * @return
     * @throws Exception
     */
    Page<Achievement> getAchievementByLimit(Page<Achievement> page, boolean sort) throws Exception;

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

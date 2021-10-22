package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;

import java.util.List;

/**
 * 成就服务
 *
 * @author MysticalDream
 */
public interface AchievementService {
    /**
     * 添加成就
     *
     * @param achievement
     * @return
     * @throws Exception
     */
    int addAchievement(Achievement achievement) throws Exception;

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
     * @return
     * @throws Exception
     */
    Page<Achievement> getAchievementByLimit(Page<Achievement> page) throws Exception;
}

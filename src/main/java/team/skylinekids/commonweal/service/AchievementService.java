package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.pojo.vo.AchievementVO;

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
     * @deprecated
     */
    Page<Achievement> getAchievementByLimit(Page<Achievement> page) throws Exception;

    /**
     * 分页获取成就展示
     *
     * @param page
     * @param userId
     * @return
     * @throws Exception
     */
    Page<AchievementVO> getAchievementVOByLimit(Page<AchievementVO> page, Integer userId) throws Exception;

    /**
     * 获取成就的点赞数前三的列表
     *
     * @return
     * @throws Exception
     */
    List<Achievement> getAchievementTopThree() throws Exception;
}

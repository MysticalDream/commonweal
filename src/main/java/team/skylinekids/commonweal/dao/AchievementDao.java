package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.dao.core.GenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.pojo.vo.AchievementVO;

import java.util.List;

/**
 * @author MysticalDream
 */
public interface AchievementDao extends GenericBaseDao<Achievement> {
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
    Page<AchievementVO> getAchievementByLimit(Page<AchievementVO> page, boolean sort) throws Exception;

    /**
     * 根据所给状态更新点赞数
     *
     * @param status
     * @param achievement
     * @return
     * @throws Exception
     */
    int updateLoveNumber(Achievement achievement, boolean status) throws Exception;

    /**
     * 获取成就点赞前三名的列表
     *
     * @return
     * @throws Exception
     */
    List<Achievement> getAchievementTopThree() throws Exception;
}

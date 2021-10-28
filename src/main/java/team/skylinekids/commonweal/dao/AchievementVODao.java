package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.AchievementVO;

/**
 * 成就展示
 *
 * @author MysticalDream
 */
public interface AchievementVODao {
    /**
     * 分页获取成就
     *
     * @param page
     * @param sort   为true是升序 false为降序
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    Page<AchievementVO> getAchievementVOList(Page<AchievementVO> page, boolean sort, Integer userId) throws Exception;
}

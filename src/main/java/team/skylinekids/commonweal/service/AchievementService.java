package team.skylinekids.commonweal.service;

/**
 * 成就服务
 *
 * @author MysticalDream
 */
public interface AchievementService {
    /**
     * 根据对应类型添加成就
     *
     * @param type
     * @param achievement
     * @return
     * @throws Exception
     */
    int addAchievement(Integer type, Object achievement) throws Exception;
}

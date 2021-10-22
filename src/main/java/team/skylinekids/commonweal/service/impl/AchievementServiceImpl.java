package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.service.AchievementService;

/**
 * 添加成就
 *
 * @author MysticalDream
 */
public class AchievementServiceImpl implements AchievementService {

    AchievementDao achievementDao = DaoFactory.getAchievementDao();

    @Override
    public int addAchievement(Achievement achievement) throws Exception {
        return achievementDao.addAchievement(achievement);
    }

    @Override
    public Page<Achievement> getAchievementByLimit(Page<Achievement> page) throws Exception {
        return achievementDao.getAchievementByLimit(page, false);
    }

}

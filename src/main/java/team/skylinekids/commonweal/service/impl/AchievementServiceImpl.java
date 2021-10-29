package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.dao.AchievementVODao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.pojo.vo.AchievementVO;
import team.skylinekids.commonweal.service.AchievementService;

import java.util.List;

/**
 * 添加成就
 *
 * @author MysticalDream
 */
public class AchievementServiceImpl implements AchievementService {

    AchievementDao achievementDao = DaoFactory.getAchievementDao();
    AchievementVODao achievementVODao = DaoFactory.getAchievementVODao();

    @Override
    public int addAchievement(Achievement achievement) throws Exception {
        return achievementDao.addAchievement(achievement);
    }

    @Override
    public List<Achievement> getAchievementList(Achievement achievement) throws Exception {
        return achievementDao.getAchievementList(achievement);
    }

    @Override
    public Page<Achievement> getAchievementByLimit(Page<Achievement> page) throws Exception {
        return achievementDao.getAchievementByLimit(page, false);
    }

    @Override
    public Page<AchievementVO> getAchievementVOByLimit(Page<AchievementVO> page, Integer userId) throws Exception {
        return achievementVODao.getAchievementVOList(page, false, userId);
    }

    @Override
    public List<Achievement> getAchievementTopThree() throws Exception {
        return achievementDao.getAchievementTopThree();
    }


}

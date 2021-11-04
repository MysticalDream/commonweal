package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.dao.AchievementVODao;
import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.TeamDao;
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
    TeamDao teamDao = DaoFactory.getTeamDao();
    ItemDao itemDao = DaoFactory.getItemDao();

    @Override
    public int addAchievement(Achievement achievement) throws Exception {
        return achievementDao.addAchievement(achievement);
    }

    @Override
    public List<Achievement> getAchievementList(Achievement achievement) throws Exception {
        return achievementDao.getAchievementList(achievement);
    }

    @Override
    public Page<AchievementVO> getAchievementByLimit(Page<AchievementVO> page) throws Exception {
        Page<AchievementVO> achievementByLimit = achievementDao.getAchievementByLimit(page, false);
        for (AchievementVO achievementVO :
                achievementByLimit.getList()) {
            achievementVO.setLoved(null);
            //项目成就
            if (achievementVO.getType().equals(1)) {
                achievementVO.setName(itemDao.getItemNameById(achievementVO.getTypeId()));
            } else {
                //队伍成就
                achievementVO.setName(teamDao.getTeamNameById(achievementVO.getTypeId()));
            }
        }
        return achievementByLimit;
    }

    @Override
    public Page<AchievementVO> getAchievementVOByLimit(Page<AchievementVO> page, Integer userId) throws Exception {
        Page<AchievementVO> achievementVOList = achievementVODao.getAchievementVOList(page, false, userId);
        List<AchievementVO> list = achievementVOList.getList();
        for (AchievementVO achievementVO :
                list) {
            //项目成就
            if (achievementVO.getType().equals(1)) {
                achievementVO.setName(itemDao.getItemNameById(achievementVO.getTypeId()));
            } else {
                //队伍成就
                achievementVO.setName(teamDao.getTeamNameById(achievementVO.getTypeId()));
            }
        }
        return achievementVOList;
    }

    @Override
    public List<Achievement> getAchievementTopThree() throws Exception {
        return achievementDao.getAchievementTopThree();
    }


}

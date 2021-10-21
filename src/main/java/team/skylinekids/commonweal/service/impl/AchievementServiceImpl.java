package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemAchievementDao;
import team.skylinekids.commonweal.dao.TeamAchievementDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.ItemAchievement;
import team.skylinekids.commonweal.pojo.po.TeamAchievement;
import team.skylinekids.commonweal.service.AchievementService;

/**
 * 添加成就
 *
 * @author MysticalDream
 */
public class AchievementServiceImpl implements AchievementService {

    TeamAchievementDao teamAchievementDao = DaoFactory.getTeamAchievementDao();

    ItemAchievementDao itemAchievementDao = DaoFactory.getItemAchievementDao();

    @Override
    public int addAchievement(Integer type, Object achievement) throws Exception {
        //项目成就
        if (type.intValue() == 1) {
            ItemAchievement itemAchievement = (ItemAchievement) achievement;
            return itemAchievementDao.addItemAchievement(itemAchievement);
        }
        //团队成就
        else {
            TeamAchievement teamAchievement = (TeamAchievement) achievement;
            return teamAchievementDao.addTeamAchievement(teamAchievement);
        }
    }
}

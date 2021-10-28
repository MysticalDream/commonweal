package team.skylinekids.commonweal.service.impl;


import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.dao.ItemAchievementDao;
import team.skylinekids.commonweal.dao.TeamAchievementDao;
import team.skylinekids.commonweal.dao.ThumbDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.Achievement;
import team.skylinekids.commonweal.pojo.po.Thumb;
import team.skylinekids.commonweal.service.ThumbService;

/**
 * 点赞服务实现类
 *
 * @author MysticalDream
 */
public class ThumbServiceImpl implements ThumbService {

    ThumbDao thumbDao = DaoFactory.getThumbDao();

    ItemAchievementDao itemAchievementDao = DaoFactory.getItemAchievementDao();

    TeamAchievementDao teamAchievementDao = DaoFactory.getTeamAchievementDao();

    AchievementDao achievementDao = DaoFactory.getAchievementDao();


    @Override
    public void handleThumb(Thumb thumb) throws Exception {
        boolean clickLike = thumbDao.clickLike(thumb);
        Achievement achievement = new Achievement();
        achievement.setId(thumb.getAchievementId());
        //成功的点赞
        if (clickLike) {
            //项目成就
            achievementDao.updateLoveNumber(achievement, true);
            //取消点赞
        } else {
            achievementDao.updateLoveNumber(achievement, false);
        }

    }
}

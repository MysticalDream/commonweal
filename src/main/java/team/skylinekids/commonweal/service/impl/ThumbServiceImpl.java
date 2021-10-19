package team.skylinekids.commonweal.service.impl;


import team.skylinekids.commonweal.dao.ItemAchievementDao;
import team.skylinekids.commonweal.dao.TeamAchievementDao;
import team.skylinekids.commonweal.dao.ThumbDao;
import team.skylinekids.commonweal.factory.DaoFactory;
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

    @Override
    public void handleThumb(Thumb thumb) throws Exception {
        boolean clickLike = thumbDao.clickLike(thumb);
        Integer type = thumb.getType();
        //成功的点赞
        if (clickLike) {
            //项目成就
            if (type == 1) {
                itemAchievementDao.updateLoveNumber(thumb.getTypeId(), true);
            }
            //团队成就
            else {
                teamAchievementDao.updateLoveNumber(thumb.getTypeId(), true);
            }
            //取消点赞
        } else {
            //项目成就
            if (type == 1) {
                itemAchievementDao.updateLoveNumber(thumb.getTypeId(), false);
            }
            //团队成就
            else {
                teamAchievementDao.updateLoveNumber(thumb.getTypeId(), false);
            }
        }

    }
}

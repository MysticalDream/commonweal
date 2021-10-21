package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.AchievementDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.Achievement;

import java.util.List;

public class AchievementDaoImpl extends MyGenericBaseDao<Achievement> implements AchievementDao {
    @Override
    public int addAchievement(Achievement achievement) throws Exception {
        return this.insert(achievement);
    }

    @Override
    public int updateAchievement(Achievement achievement) throws Exception {
        return 0;
    }

    @Override
    public List<Achievement> getAchievementById(Achievement achievement) throws Exception {
        return this.selectList(achievement);
    }

    @Override
    public int updateLoveNumber(Achievement achievement, boolean status) throws Exception {

        return 0;
    }
}

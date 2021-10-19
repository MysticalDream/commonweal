package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.TeamAchievementDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.TeamAchievement;
import team.skylinekids.commonweal.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * 团队成就实现类
 *
 * @author MysticalDream
 */
public class TeamAchievementDaoImpl extends MyGenericBaseDao<TeamAchievement> implements TeamAchievementDao {
    @Override
    public int addTeamAchievement(TeamAchievement teamAchievement) throws Exception {
        return 0;
    }

    @Override
    public int updateTeamAchievement(TeamAchievement teamAchievement) throws Exception {
        return 0;
    }

    @Override
    public List<TeamAchievement> getTeamAchievementById(int id) throws Exception {
        return null;
    }

    @Override
    public int updateLoveNumber(Integer teamAchievementId, boolean status) throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String res = "";
        if (status) {
            res = "+1";
        } else {
            res = "-1";
        }
        String sql = "UPDATE " + this.getTableName() + "SET love_number=love_number" + res + " WHERE id=?";
        return this.update(connection, sql, teamAchievementId);
    }
}

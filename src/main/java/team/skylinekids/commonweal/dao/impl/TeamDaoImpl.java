package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.Team;

/**
 * @author MysticalDream
 */
public class TeamDaoImpl extends MyGenericBaseDao<Team> implements TeamDao {
    @Override
    public int addTeam(Team team) throws Exception {
        return this.insert(team);
    }

    @Override
    public Team getTeamById(int id) throws Exception {
        return this.selectByPrimaryKey(id);
    }

    @Override
    public int updateTeam(Team team) throws Exception {
        return this.update(team);
    }
}

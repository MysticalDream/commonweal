package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.query.TeamCondition;
import team.skylinekids.commonweal.service.TeamService;

/**
 * @author MysticalDream
 */
public class TeamServiceImpl implements TeamService {

    TeamDao teamDao = DaoFactory.getTeamDao();

    @Override
    public int createTeam(Team team) throws Exception {
        return teamDao.addTeam(team);
    }

    @Override
    public Team getTeamById(int id) throws Exception {
        return teamDao.getTeamById(id);
    }

    @Override
    public int updateTeam(Team team) throws Exception {
        return teamDao.updateTeam(team);
    }

    @Override
    public Page<TeamDTO> getTeamByCondition(TeamCondition teamCondition) throws Exception {
        return teamDao.getTeamByCondition(teamCondition);
    }
}

package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.query.TeamCondition;
import team.skylinekids.commonweal.service.TeamService;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.List;

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

    @Override
    public List<TeamDTO> getTeamsByUserId(Integer userId) throws Exception {

        List<Team> teams = teamDao.getTeamsByUserId(userId);
        List<TeamDTO> teamDTOS = ConversionUtils.convertList(teams, TeamDTO.class);
        for (TeamDTO teamDTO :
                teamDTOS) {
            teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + teamDTO.getTeamAvatar());
        }
        return teamDTOS;
    }

    @Override
    public List<TeamDTO> getUserJoinedTeam(Integer userId) throws Exception {
        List<Team> teams = teamDao.getUserJoinedTeam(userId);
        List<TeamDTO> teamDTOS = ConversionUtils.convertList(teams, TeamDTO.class);
        for (TeamDTO teamDTO :
                teamDTOS) {
            teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + teamDTO.getTeamAvatar());
        }
        return teamDTOS;
    }

}

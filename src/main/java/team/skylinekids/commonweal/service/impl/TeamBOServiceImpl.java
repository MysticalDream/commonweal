package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.TeamBO;
import team.skylinekids.commonweal.pojo.dto.TeamDTO;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.Team;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.TeamBOService;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.List;

/**
 * @author MysticalDream
 */
public class TeamBOServiceImpl implements TeamBOService {

    TeamDao teamDao = DaoFactory.getTeamDao();

    UserDao userDao = DaoFactory.getUserDao();

    @Override
    public TeamBO getTeamBOByTeamId(Integer teamId) throws Exception {

        Team team = teamDao.getTeamById(teamId);

        TeamDTO teamDTO = ConversionUtils.convert(team, TeamDTO.class);
        teamDTO.setTeamAvatar(ResourcePathConstant.VIRTUAL_TEAM_COVER_BASE + team.getTeamAvatar());

        List<User> teamUserList = userDao.getTeamUserList(teamId);

        List<UserDTO> userDTOS = ConversionUtils.convertList(teamUserList, UserDTO.class);

        for (UserDTO userDTO :
                userDTOS) {
            userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());
        }
        return new TeamBO(teamDTO, userDTOS);
    }
}

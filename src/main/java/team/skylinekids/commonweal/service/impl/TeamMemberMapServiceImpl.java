package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.dao.TeamMemberMapDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.TeamMemberMap;
import team.skylinekids.commonweal.service.TeamMemberMapService;

/**
 * 团队成员映射
 *
 * @author MysticalDream
 */
public class TeamMemberMapServiceImpl implements TeamMemberMapService {

    TeamMemberMapDao teamMemberMapDao = DaoFactory.getTeamMemberMapDao();

    TeamDao teamDao = DaoFactory.getTeamDao();

    @Override
    public int addMember(TeamMemberMap teamMemberMap) throws Exception {
        teamMemberMapDao.addMember(teamMemberMap);
        return teamDao.updateNowMen(teamMemberMap.getTeamId(), 1);
    }

    @Override
    public int removeMember(TeamMemberMap teamMemberMap) throws Exception {
        return teamMemberMapDao.removeMember(teamMemberMap);
    }
}

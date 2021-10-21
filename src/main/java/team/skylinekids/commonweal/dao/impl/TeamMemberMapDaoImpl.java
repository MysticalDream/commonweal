package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.TeamMemberMapDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.TeamMemberMap;

/**
 * 团队成员映射Dao
 *
 * @author MysticalDream
 */
public class TeamMemberMapDaoImpl extends MyGenericBaseDao<TeamMemberMap> implements TeamMemberMapDao {
    @Override
    public int addMember(TeamMemberMap teamMemberMap) throws Exception {
        return this.insert(teamMemberMap);
    }

    @Override
    public int removeMember(TeamMemberMap teamMemberMap) throws Exception {
        teamMemberMap.setAvailable(false);
        return this.update(teamMemberMap);
    }
}

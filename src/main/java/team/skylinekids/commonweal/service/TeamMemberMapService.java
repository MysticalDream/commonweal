package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.TeamMemberMap;

/**
 * 团队成员
 *
 * @author MysticalDream
 */
public interface TeamMemberMapService {
    /**
     * 添加队伍成员
     *
     * @param teamMemberMap
     * @return
     * @throws Exception
     */
    int addMember(TeamMemberMap teamMemberMap) throws Exception;

    /**
     * 移除队伍成员
     *
     * @param teamMemberMap
     * @return
     * @throws Exception
     */
    int removeMember(TeamMemberMap teamMemberMap) throws Exception;
}

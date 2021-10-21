package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.TeamBO;

public interface TeamBOService {
    /**
     * 根据团队id获取项目业务对象
     * @param teamId
     * @return
     * @throws Exception
     */
    TeamBO getTeamBOByTeamId(Integer teamId) throws Exception;
}

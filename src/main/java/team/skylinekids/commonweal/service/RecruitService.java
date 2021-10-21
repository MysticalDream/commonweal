package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.po.Recruit;
import team.skylinekids.commonweal.pojo.query.RecruitCondition;

/**
 * 招募志愿者
 *
 * @author MysticalDream
 */
public interface RecruitService {
    /**
     * 添加招募志愿者信息
     *
     * @param recruit
     * @return
     */
    int addRecruit(Recruit recruit) throws Exception;

    /**
     * 条件查询招募志愿者信息
     *
     * @param recruitCondition
     * @return
     * @throws Exception
     */
    Page<RecruitDTO> getRecruitByCondition(RecruitCondition recruitCondition) throws Exception;
}

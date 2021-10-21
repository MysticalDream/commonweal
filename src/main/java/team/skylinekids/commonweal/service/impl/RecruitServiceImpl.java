package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.RecruitDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.po.Recruit;
import team.skylinekids.commonweal.pojo.query.RecruitCondition;
import team.skylinekids.commonweal.service.RecruitService;

/**
 * @author MysticalDream
 */
public class RecruitServiceImpl implements RecruitService {

    RecruitDao recruitDao = DaoFactory.getRecruitDao();

    @Override
    public int addRecruit(Recruit recruit) throws Exception {
        return recruitDao.addRecruit(recruit);
    }

    @Override
    public Page<RecruitDTO> getRecruitByCondition(RecruitCondition recruitCondition) throws Exception {
        return recruitDao.getByConditionString(recruitCondition);
    }
}

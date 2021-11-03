package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ProvinceDao;
import team.skylinekids.commonweal.dao.RecruitDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.pojo.po.Recruit;
import team.skylinekids.commonweal.pojo.query.RecruitCondition;
import team.skylinekids.commonweal.service.RecruitService;

/**
 * @author MysticalDream
 */
public class RecruitServiceImpl implements RecruitService {

    RecruitDao recruitDao = DaoFactory.getRecruitDao();
    ProvinceDao provinceDao = DaoFactory.getProvinceDao();

    @Override
    public int addRecruit(Recruit recruit) throws Exception {
        return recruitDao.addRecruit(recruit);
    }

    @Override
    public Page<RecruitDTO> getRecruitByCondition(RecruitCondition recruitCondition) throws Exception {
        Page<RecruitDTO> byConditionString = recruitDao.getByConditionString(recruitCondition);
        for (RecruitDTO recruitDTO :
                byConditionString.getList()) {
            Province province = new Province(recruitDTO.getProvince(), recruitDTO.getCity(), recruitDTO.getArea());
            recruitDTO.setLocation(provinceDao.getCityNameByCondition(province));
        }
        return byConditionString;
    }
}

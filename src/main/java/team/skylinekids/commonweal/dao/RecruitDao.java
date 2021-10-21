package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.RecruitDTO;
import team.skylinekids.commonweal.pojo.po.Recruit;
import team.skylinekids.commonweal.pojo.query.RecruitCondition;

/**
 * 招募志愿Dao
 *
 * @author MysticalDream
 */
public interface RecruitDao {

    /**
     * 添加招募信息l
     *
     * @param recruit
     * @return
     * @throws Exception
     */
    int addRecruit(Recruit recruit) throws Exception;


    /**
     * 条件获取招募志愿者信息
     *
     * @param recruitCondition
     * @return
     * @throws Exception
     */
    Page<RecruitDTO> getByConditionString(RecruitCondition recruitCondition) throws Exception;
}

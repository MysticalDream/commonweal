package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Adopt;

import java.util.List;

/**
 * 领养
 *
 * @author MysticalDream
 */
public interface AdoptService {
    /**
     * 添加领养信息
     *
     * @param adopt
     * @return
     * @throws Exception
     */
    int addAdoptInfo(Adopt adopt) throws Exception;

    /**
     * 获取领养信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    Adopt getAdoptById(Integer id) throws Exception;

    /**
     * 获取领养动物列表
     *
     * @param page
     * @param option
     * @return
     * @throws Exception
     */
    Page<Adopt> getAdoptList(Page<Adopt> page, boolean option) throws Exception;

    /**
     * 更新领养动物的用户的id
     *
     * @param adopt
     * @return
     * @throws Exception
     */
    int updateAdoptUserId(Adopt adopt) throws Exception;

    /**
     * 获取用户已领养的动物
     *
     * @param userId
     * @param adoptPage
     * @return
     * @throws Exception
     */
    List<Adopt> getUserAdopt(Integer userId, Page<Adopt> adoptPage) throws Exception;
}

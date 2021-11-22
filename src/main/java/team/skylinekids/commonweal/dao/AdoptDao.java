package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Adopt;

/**
 * 领养动物
 *
 * @author MysticalDream
 */
public interface AdoptDao {
    /**
     * 添加领养动物信息
     *
     * @param adopt
     * @return
     * @throws Exception
     */
    int addAdoptInfo(Adopt adopt) throws Exception;

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

}

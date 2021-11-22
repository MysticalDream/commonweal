package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Adopt;

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
     * 获取领养动物列表
     *
     * @param page
     * @param option
     * @return
     * @throws Exception
     */
    Page<Adopt> getAdoptList(Page<Adopt> page, boolean option) throws Exception;
}

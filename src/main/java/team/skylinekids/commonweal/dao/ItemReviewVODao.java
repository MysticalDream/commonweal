package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemReviewVO;

/**
 * 查询项目审核列表结果
 * @author MysticalDream
 */
public interface ItemReviewVODao {
    /**
     * 获取列表
     *
     * @param page
     * @return
     * @throws Exception
     */
    Page<ItemReviewVO> getList(Page<ItemReviewVO> page,Integer itemId) throws Exception;
}

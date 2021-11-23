package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemReviewVO;

import java.util.List;

/**
 * 审核项目列表视图
 *
 * @author MysticalDream
 */
public interface ItemReviewVOService {
    /**
     * 获取审核项目视图
     *
     * @param pageSize
     * @param pageNum
     * @return
     * @throws Exception
     */
    Page<ItemReviewVO> getList(int pageSize, int pageNum) throws Exception;
}

package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemMemberVO;

/**
 * 查询项目审核列表结果
 *
 * @author MysticalDream
 */
public interface ItemMemberDao {
    /**
     * 获取审核列表
     *
     * @param page
     * @return
     * @throws Exception
     */
    Page<ItemMemberVO> getList(Page<ItemMemberVO> page, Integer itemId) throws Exception;

    /**
     * 获取成员列表
     *
     * @param page
     * @param itemId
     * @return
     * @throws Exception
     */
    Page<ItemMemberVO> getMemberList(Page<ItemMemberVO> page, Integer itemId) throws Exception;
}

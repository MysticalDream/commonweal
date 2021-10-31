package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.pojo.vo.ItemMemberVO;

import java.util.List;

/**
 * 项目业务服务层接口
 *
 * @author MysticalDream
 */
public interface ItemService {
    /**
     * 创建项目
     *
     * @param item
     * @return
     */
    int createItem(Item item) throws Exception;

    /**
     * 根据项目id获取项目信息
     *
     * @param id
     * @return
     */
    Item getItemById(int id) throws Exception;

    /**
     * 更新项目
     *
     * @param item
     * @return
     */
    int updateItem(Item item) throws Exception;

    /**
     * 根据条件查询项目
     *
     * @param itemCondition
     * @return
     * @throws Exception
     */
    Page<ItemDTO> getItemByCondition(ItemCondition itemCondition) throws Exception;

    /**
     * 根据用户id获取项目列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<ItemDTO> getItemsByUserId(Integer id) throws Exception;

    /**
     * 获取用户参加的项目
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<ItemDTO> getUserEnterItemList(Integer id) throws Exception;

    /**
     * 获取项目审核列表
     *
     * @param page
     * @param id
     * @return
     * @throws Exception
     */
    Page<ItemMemberVO> getItemReviewVOList(Page<ItemMemberVO> page, Integer id) throws Exception;

    /**
     * 获取项目成员列表
     *
     * @param page
     * @param id
     * @return
     * @throws Exception
     */
    Page<ItemMemberVO> getItemMemberVoList(Page<ItemMemberVO> page, Integer id) throws Exception;

    /**
     * 获取项目根据Item条件
     *
     * @param item
     * @return
     * @throws Exception
     */
    Item getItemByItemEntity(Item item) throws Exception;
}

package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;

import java.util.List;

/**
 * 项目dao
 *
 * @author MysticalDream
 */
public interface ItemDao {
    /**
     * 添加项目
     *
     * @param item
     * @return
     * @throws Exception
     */
    int addItem(Item item) throws Exception;

    /**
     * 通过项目id获取项目信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    Item getItemById(int id) throws Exception;

    /**
     * 根据分类id获取项目列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<Item> getItemByCategoryId(int id) throws Exception;

    /**
     * 更新项目
     *
     * @param item
     * @return
     */
    int updateItem(Item item) throws Exception;

    /**
     * 更新项目当前人数
     *
     * @param num
     * @return
     * @throws Exception
     */
    int updateItemNowMen(Integer itemId, Integer num) throws Exception;


    /**
     * 条件查询项目
     *
     * @param itemCondition
     * @return
     * @throws Exception
     */
    Page<ItemDTO> getByConditionString(ItemCondition itemCondition) throws Exception;

    /**
     * 根据用户id获取项目列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<Item> getItemsByUserId(Integer id) throws Exception;

    /**
     * 根据用户id获取用户参加的项目
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<Item> getUserEnterItemList(Integer id) throws Exception;

    /**
     * 获取项目更具item条件
     *
     * @param item
     * @return
     * @throws Exception
     */
    Item getItemByItemEntity(Item item) throws Exception;
}

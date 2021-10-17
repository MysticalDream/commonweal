package team.skylinekids.commonweal.dao;

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
     * 条件查询项目
     *
     * @param itemCondition
     * @return
     * @throws Exception
     */
    List<Item> getByConditionString(ItemCondition itemCondition) throws Exception;

}

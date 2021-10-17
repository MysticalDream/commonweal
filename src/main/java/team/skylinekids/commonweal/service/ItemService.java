package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;

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
    List<Item> getItemByCondition(ItemCondition itemCondition) throws Exception;

}

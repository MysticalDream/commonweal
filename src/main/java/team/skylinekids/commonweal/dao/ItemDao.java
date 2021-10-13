package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.Item;

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

    Item getItemById(int id) throws Exception;

    Item getItem(Item item) throws Exception;

    int updateItem(Item item);
}

package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.Item;

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
}

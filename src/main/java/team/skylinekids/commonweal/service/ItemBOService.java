package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.ItemBO;

/**
 * 项目业务服务
 *
 * @author MysticalDream
 */
public interface ItemBOService {
    /**
     * 根据项目id获取项目业务对象
     *
     * @param itemId
     * @return
     */
    ItemBO getItemBOByItemId(Integer itemId) throws Exception;
}

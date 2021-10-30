package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.ItemMemberMap;

/**
 * @author MysticalDream
 */
public interface ItemMemberMapService {
    /**
     * 添加项目成员
     *
     * @param itemMemberMap
     * @return
     * @throws Exception
     */
    int addMemberMap(ItemMemberMap itemMemberMap) throws Exception;


    /**
     * 移除成员
     *
     * @param itemMemberMap
     * @return
     * @throws Exception
     */
    int removeMember(ItemMemberMap itemMemberMap) throws Exception;

    /**
     * 更新项目成员映射
     *
     * @param itemMemberMap
     * @return
     * @throws Exception
     */
    int checkMemberMap(ItemMemberMap itemMemberMap) throws Exception;

    /**
     * 根据id获取成员映射
     *
     * @param id
     * @return
     * @throws Exception
     */
    ItemMemberMap getItemMemberById(Integer id) throws Exception;

}

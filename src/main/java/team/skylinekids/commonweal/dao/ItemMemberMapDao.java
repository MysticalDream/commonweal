package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.ItemMemberMap;

/**
 * 项目成员映射表
 *
 * @author MysticalDream
 */
public interface ItemMemberMapDao {
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
    int updateMemberMap(ItemMemberMap itemMemberMap) throws Exception;

    /**
     * 根据id获取项目成员映射对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    ItemMemberMap getItemMemberMapById(Integer id) throws Exception;

}

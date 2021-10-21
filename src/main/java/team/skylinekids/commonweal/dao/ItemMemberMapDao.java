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
    int addMember(ItemMemberMap itemMemberMap) throws Exception;

    /**
     * 移除成员
     *
     * @param itemMemberMap
     * @return
     * @throws Exception
     */
    int removeMember(ItemMemberMap itemMemberMap) throws Exception;

}

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

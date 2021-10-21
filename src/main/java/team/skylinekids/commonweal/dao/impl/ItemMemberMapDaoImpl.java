package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.ItemMemberMapDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.ItemMemberMap;

/**
 * 团队成员映射Dao
 *
 * @author MysticalDream
 */
public class ItemMemberMapDaoImpl extends MyGenericBaseDao<ItemMemberMap> implements ItemMemberMapDao {

    @Override
    public int addMember(ItemMemberMap itemMemberMap) throws Exception {
        return this.insert(itemMemberMap);
    }

    @Override
    public int removeMember(ItemMemberMap itemMemberMap) throws Exception {
        itemMemberMap.setAvailable(false);
        return this.update(itemMemberMap);
    }
}

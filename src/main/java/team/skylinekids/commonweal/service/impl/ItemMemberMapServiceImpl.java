package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemMemberMapDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.ItemMemberMap;
import team.skylinekids.commonweal.service.ItemMemberMapService;

/**
 * @author MysticalDream
 */
public class ItemMemberMapServiceImpl implements ItemMemberMapService {
    ItemMemberMapDao itemMemberMapDao = DaoFactory.getItemMemberMapDao();

    @Override
    public int addMember(ItemMemberMap itemMemberMap) throws Exception {
        return itemMemberMapDao.addMember(itemMemberMap);

    }

    @Override
    public int removeMember(ItemMemberMap itemMemberMap) throws Exception {

        return itemMemberMapDao.removeMember(itemMemberMap);
    }
}

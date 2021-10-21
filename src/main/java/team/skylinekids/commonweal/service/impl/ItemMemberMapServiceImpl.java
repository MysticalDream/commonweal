package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.ItemMemberMapDao;
import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.ItemMemberMap;
import team.skylinekids.commonweal.service.ItemMemberMapService;

/**
 * @author MysticalDream
 */
public class ItemMemberMapServiceImpl implements ItemMemberMapService {

    ItemMemberMapDao itemMemberMapDao = DaoFactory.getItemMemberMapDao();

    ItemDao itemDao = DaoFactory.getItemDao();

    TeamDao teamDao = DaoFactory.getTeamDao();

    @Override
    public int addMember(ItemMemberMap itemMemberMap) throws Exception {
        //个人
        if (itemMemberMap.getType()) {
            itemDao.updateItemNowMen(itemMemberMap.getItemId(), 1);
        }
        //团队形式
        else {
            itemDao.updateItemNowMen(itemMemberMap.getItemId(), teamDao.getTeamById(itemMemberMap.getTargetId()).getNowMen());
        }
        return itemMemberMapDao.addMember(itemMemberMap);

    }

    @Override
    public int removeMember(ItemMemberMap itemMemberMap) throws Exception {

        return itemMemberMapDao.removeMember(itemMemberMap);
    }
}

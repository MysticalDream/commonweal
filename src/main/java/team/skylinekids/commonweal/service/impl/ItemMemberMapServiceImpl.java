package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.ItemMemberMapDao;
import team.skylinekids.commonweal.dao.TeamDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.po.Item;
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
    public int addMemberMap(ItemMemberMap itemMemberMap) throws Exception {
        return itemMemberMapDao.addMemberMap(itemMemberMap);
    }

    @Override
    public int removeMember(ItemMemberMap itemMemberMap) throws Exception {
        return itemMemberMapDao.removeMember(itemMemberMap);
    }

    @Override
    public int checkMemberMap(ItemMemberMap itemMemberMap) throws Exception {
        ItemMemberMap memberMapTemp = new ItemMemberMap();
        memberMapTemp.setId(itemMemberMap.getId());
        memberMapTemp.setStatus(itemMemberMap.getStatus());
        if (itemMemberMap.getStatus() == 1) {
            Item item = itemDao.getItemById(itemMemberMap.getItemId());
            Integer maxMen = item.getMaxMen();
            Integer nowMen = item.getNowMen();
            //个人
            if (itemMemberMap.getType()) {
                if (nowMen + 1 > maxMen) {
                    return -1;
                }
                itemDao.updateItemNowMen(itemMemberMap.getItemId(), 1);
            }
            //团队形式
            else {
                Integer nowMen1 = teamDao.getTeamById(itemMemberMap.getTargetId()).getNowMen();
                if (nowMen + nowMen1 > maxMen) {
                    return -1;
                }
                itemDao.updateItemNowMen(itemMemberMap.getItemId(), nowMen1);
            }
            return itemMemberMapDao.updateMemberMap(memberMapTemp);
        }
        return itemMemberMapDao.updateMemberMap(memberMapTemp);
    }

    @Override
    public ItemMemberMap getItemMemberById(Integer id) throws Exception {
        return itemMemberMapDao.getItemMemberMapById(id);
    }


}

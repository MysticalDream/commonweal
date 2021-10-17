package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.service.ItemService;

/**
 * 项目服务实现类
 *
 * @author MysticalDream
 */
public class ItemServiceImpl implements ItemService {

    ItemDao itemDao = DaoFactory.getItemDao();

    @Override
    public int createItem(Item item) throws Exception {
        return itemDao.addItem(item);
    }

    @Override
    public Item getItemById(int id) throws Exception {
        return itemDao.getItemById(id);
    }

    @Override
    public int updateItem(Item item) throws Exception {
        return itemDao.updateItem(item);
    }

    @Override
    public Page<ItemDTO> getItemByCondition(ItemCondition itemCondition) throws Exception {
        return itemDao.getByConditionString(itemCondition);
    }
}

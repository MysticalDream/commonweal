package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.core.GenericBaseMapper;
import team.skylinekids.commonweal.pojo.po.Item;

import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemDaoImpl extends GenericBaseMapper<Item> implements ItemDao {
    @Override
    public int addItem(Item item) throws Exception {
        return this.insert(item);
    }

    @Override
    public Item getItemById(int id) throws Exception {
        return this.selectByPrimaryKey(id);
    }

    @Override
    public List<Item> getItemByCategoryId(int id) throws Exception {
        Item item = new Item();
        item.setItemCategoryId(id);
        return this.selectList(item);
    }

    @Override
    public int updateItem(Item item) throws Exception {
        return this.update(item);
    }
}

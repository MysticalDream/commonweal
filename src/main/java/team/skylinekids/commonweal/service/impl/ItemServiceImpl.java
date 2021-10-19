package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.service.ItemService;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ItemDTO> getItemsByUserId(Integer id) throws Exception {
        List<Item> items = itemDao.getItemsByUserId(id);
        List<ItemDTO> itemDTOs = new ArrayList<>(items.size());
        for (Item item :
                items) {
            ItemDTO itemDTO = ConversionUtils.convert(item, ItemDTO.class);
            itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());
            itemDTO.setItemCategory(CategoryUtils.getCategoryNameById(item.getItemCategoryId()));
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }

    @Override
    public List<ItemDTO> getUserEnterItemList(Integer id) throws Exception {
        List<Item> items = itemDao.getUserEnterItemList(id);
        List<ItemDTO> itemDTOs = new ArrayList<>(items.size());
        for (Item item :
                items) {
            ItemDTO itemDTO = ConversionUtils.convert(item, ItemDTO.class);
            itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());
            itemDTO.setItemCategory(CategoryUtils.getCategoryNameById(item.getItemCategoryId()));
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }
}

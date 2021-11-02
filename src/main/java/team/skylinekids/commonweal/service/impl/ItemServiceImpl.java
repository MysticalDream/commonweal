package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.ItemMemberDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.pojo.vo.ItemMemberVO;
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

    ItemMemberDao itemMemberDao = DaoFactory.getItemReviewVODao();

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
    public Page<ItemDTO> getItemsByUserId(Page<ItemDTO> page, Integer id) throws Exception {
        Page<ItemDTO> items = itemDao.getItemsByUserId(page, id);
        return items;
    }

    @Override
    public Page<ItemDTO> getUserEnterItemList(Page<ItemDTO> page, Integer id) throws Exception {
        Page<ItemDTO> userEnterItemList = itemDao.getUserEnterItemList(page, id);
        return userEnterItemList;
    }

    @Override
    public Page<ItemMemberVO> getItemReviewVOList(Page<ItemMemberVO> page, Integer id) throws Exception {
        return itemMemberDao.getList(page, id);
    }

    @Override
    public Page<ItemMemberVO> getItemMemberVoList(Page<ItemMemberVO> page, Integer id) throws Exception {
        return itemMemberDao.getMemberList(page, id);
    }

    @Override
    public Item getItemByItemEntity(Item item) throws Exception {
        return itemDao.getItemByItemEntity(item);
    }
}

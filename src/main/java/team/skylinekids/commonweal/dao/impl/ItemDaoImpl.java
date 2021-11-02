package team.skylinekids.commonweal.dao.impl;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.utils.*;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.reflect.ReflectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemDaoImpl extends MyGenericBaseDao<Item> implements ItemDao {
    private final Logger logger = Logger.getLogger(ItemDaoImpl.class);

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

    @Override
    public int updateItemNowMen(Integer itemId, Integer num) throws Exception {
        String updateSql = "UPDATE " + this.getTableName() + " SET now_men=now_men+" + num + " WHERE item_id=?";
        return this.update(JDBCUtils.getConnection(), updateSql, itemId);
    }

    @Override
    public Page<ItemDTO> getByConditionString(ItemCondition itemCondition) throws Exception {
        /**
         * 省份/直辖市
         */
        String province = itemCondition.getProvince();
        /**
         * 城市
         */
        String city = itemCondition.getCity();
        /**
         * 区县
         */
        String area = itemCondition.getArea();
        /**
         * 分类id
         */
        Integer categoryId = itemCondition.getItemCategoryId();
        /**
         * 人数范围
         */
        Integer numberScope = itemCondition.getNumberScope();
        /**
         * 项目编号
         */
        Integer itemId = itemCondition.getItemId();
        /**
         * 项目标题
         */
        String itemTitle = itemCondition.getItemTitle();
        /**
         * 每页显示数量
         */
        Integer pageSize = itemCondition.getPageSize();
        /**
         * 第几页
         */
        Integer pageNum = itemCondition.getPageNum();

        List<String> conditionSql = new ArrayList<>(6);

        List<Object> values = new ArrayList<>(6);

        if (StringUtils.isNotBlank(province)) {
            conditionSql.add("province=?");
            values.add(province);
        }
        if (StringUtils.isNotBlank(city)) {
            conditionSql.add("city=?");
            values.add(city);
        }
        if (StringUtils.isNotBlank(area)) {
            conditionSql.add("area=?");
            values.add(area);
        }
        if (categoryId != null) {
            conditionSql.add("item_category_id=?");
            values.add(categoryId);
        }

        if (numberScope != null) {
            String scope = ScopeUtils.getScopeByNum(numberScope);
            if (scope != null) {
                conditionSql.add(scope);
            }
        }

        if (itemId != null) {
            conditionSql.add("item_id=?");
            values.add(itemId);
        }

        if (StringUtils.isNotBlank(itemTitle)) {
            conditionSql.add("item_title like ?");
            values.add("%" + itemTitle + "%");
        }


        String sql = String.join(" AND ", conditionSql);
        if (!"".equals(sql)) {
            sql = " WHERE " + sql;
        }
        //分页
        Page<ItemDTO> page = new Page<>();

        page.setPageNum(pageNum);

        page.setPageSize(pageSize);

        Integer total = this.selectCountByCondition(sql, values);

        sql += " LIMIT " + page.getStartRow() + "," + page.getPageSize();

        List<Item> items = this.selectListByConditionString(sql, values);

        List<ItemDTO> itemDTOs = ConversionUtils.convertList(items, ItemDTO.class);
        //设置封面路径
        ResourceURLUtils.setItemsURL(itemDTOs);
        /**
         * 总记录数
         */
        page.setTotal(total);
        /**
         * 数据
         */
        page.setList(itemDTOs);
        /**
         * 当前页数据数量
         */
        page.setSize(itemDTOs.size());
        //计算页数
        page.setPagesAuto();

        return page;
    }

    @Override
    public Page<ItemDTO> getItemsByUserId(Page<ItemDTO> page, Integer id) throws Exception {
        String sqlCondition = " WHERE user_id=" + id;
        Integer count = this.selectCountByCondition(sqlCondition, new ArrayList<>());
        page.setTotal(count);
        List<Item> items = this.selectListByConditionString(sqlCondition + " LIMIT " + page.getStartRow() + "," + page.getPageSize(), new ArrayList<>());
        List<ItemDTO> itemDTOS = new ArrayList<>(items.size());
        for (Item item :
                items) {
            ItemDTO itemDTO = ConversionUtils.convert(item, ItemDTO.class);
            itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());
            itemDTO.setItemCategory(CategoryUtils.getCategoryNameById(item.getItemCategoryId()));
            itemDTOS.add(itemDTO);
        }
        page.setSize(itemDTOS.size());
        page.setList(itemDTOS);
        page.setPagesAuto();
        return page;
    }

    @Override
    public Page<ItemDTO> getUserEnterItemList(Page<ItemDTO> page, Integer id) throws Exception {
        String sqlCondition = " WHERE item_id IN(SELECT item_id FROM item_and_member_map WHERE target_id=" + id + " AND type=true AND status=1)";

        Integer integer = this.selectCountByCondition(sqlCondition, new ArrayList<>());
        List<Item> items = this.selectListByConditionString(sqlCondition + " LIMIT " + page.getStartRow() + "," + page.getPageSize(), new ArrayList<>());
        List<ItemDTO> itemDTOS = new ArrayList<>(items.size());
        for (Item item :
                items) {
            ItemDTO itemDTO = ConversionUtils.convert(item, ItemDTO.class);
            itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());
            itemDTO.setItemCategory(CategoryUtils.getCategoryNameById(item.getItemCategoryId()));
            itemDTOS.add(itemDTO);
        }
        page.setList(itemDTOS);
        page.setSize(itemDTOS.size());
        page.setPagesAuto();
        return page;
    }

    @Override
    public Item getItemByItemEntity(Item item) throws Exception {
        return this.selectOne(item);
    }


}

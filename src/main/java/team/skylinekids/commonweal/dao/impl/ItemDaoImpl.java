package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.utils.ScopeUtils;
import team.skylinekids.commonweal.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemDaoImpl extends MyGenericBaseDao<Item> implements ItemDao {
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
    public List<Item> getByConditionString(ItemCondition itemCondition) throws Exception {
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

        String scope = ScopeUtils.getScopeByNum(numberScope);

        if (scope != null) {
            conditionSql.add(scope);
        }

        String sql = String.join(" AND ", conditionSql);
        //分页
        Page<Item> page = new Page<>();

        page.setPageNum(pageNum);

        page.setPageSize(pageSize);

        sql += "LIMIT " + page.getStartRow() + "," + page.getPageSize();

        return this.selectListByConditionString(sql, values);

    }
}

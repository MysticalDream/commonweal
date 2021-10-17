package dao;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.impl.ItemDaoImpl;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.util.List;

class ItemDaoImplTest {
    ItemDao itemDao = new ItemDaoImpl();

    @Test
    void addItem() throws Exception {
        Item item = new Item();
        item.setItemTitle("我的项目");
        item.setItemCategoryId(1);
        item.setItemIntroduction("这是我的测试项目");
        item.setDuration("1个月");
        item.setCoverUrl("1.png");
        item.setMaxMen(100);
        item.setProvince("广东省");
        item.setCity("广州市");
        item.setUserId(1);
        System.out.println(itemDao.addItem(item));
    }

    @Test
    void getItemById() throws Exception {
        Item item = itemDao.getItemById(1);
        System.out.println(item);
    }

    @Test
    void getItemByCategoryId() throws Exception {
        List<Item> itemByCategoryId = itemDao.getItemByCategoryId(1);
        itemByCategoryId.forEach(System.out::println);
    }

    @Test
    void updateItem() throws Exception {
        Item item = new Item();
        item.setItemId(2);
        item.setItemTitle("我的更新项目");
        item.setItemIntroduction("我的测试项目介绍");
        item.setCoverUrl("123.png");
        item.setDuration("一年");
        item.setProvince("广东省");
        item.setCity("广州市");
        item.setItemCategoryId(1);
        int i = itemDao.updateItem(item);
        System.out.println(i);
    }

    @Test
    void test() throws Exception {

        ItemCondition itemCondition = GsonUtils.j2O("{\n" +
                "\t\"province\": \"44\",\n" +
                "\t\"city\": \"01\",\n" +
                "\t\"area\": \"\",\n" +
                "\t\"itemCategory\": \"社区服务\",\n" +
                "\t\"numberScope\": \"1\",\n" +
                "\t\"pageSize\": \"9\",\n" +
                "\t\"pageNum\": \"1\"\n" +
                "}", ItemCondition.class);

        Page<Item> byConditionString = itemDao.getByConditionString(itemCondition);

        byConditionString.getList().forEach(System.out::println);
    }
}
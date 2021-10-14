package dao;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.impl.ItemDaoImpl;
import team.skylinekids.commonweal.pojo.po.Item;

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
        item.setLocation("广东");
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
        item.setLocation("中国境内");
        item.setDuration("一年");
        item.setItemCategoryId(1);
        int i = itemDao.updateItem(item);
        System.out.println(i);
    }
}
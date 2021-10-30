package dao;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.ItemReviewVODao;
import team.skylinekids.commonweal.dao.impl.ItemReviewVODaoImpl;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemReviewVO;

class ItemReviewVODaoImplTest {
    ItemReviewVODao itemReviewVODao = new ItemReviewVODaoImpl();

    @Test
    void getList() throws Exception {
        Page<ItemReviewVO> page = new Page<>();
        page.setPageNum(1);
        page.setPageSize(2);
        Page<ItemReviewVO> list = itemReviewVODao.getList(page, 1);
        list.getList().forEach(System.out::println);
    }
}
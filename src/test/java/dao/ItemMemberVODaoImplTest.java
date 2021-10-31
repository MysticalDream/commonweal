package dao;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.dao.ItemMemberDao;
import team.skylinekids.commonweal.dao.impl.ItemMemberDaoImpl;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.vo.ItemMemberVO;

class ItemMemberVODaoImplTest {
    ItemMemberDao itemMemberDao = new ItemMemberDaoImpl();

    @Test
    void getList() throws Exception {
        Page<ItemMemberVO> page = new Page<>();
        page.setPageNum(1);
        page.setPageSize(2);
        Page<ItemMemberVO> list = itemMemberDao.getList(page, 1);
        list.getList().forEach(System.out::println);
    }
}
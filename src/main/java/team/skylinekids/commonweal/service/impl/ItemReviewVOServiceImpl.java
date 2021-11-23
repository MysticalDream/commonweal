package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.ItemDao;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.factory.DaoFactory2;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.pojo.vo.ItemReviewVO;
import team.skylinekids.commonweal.service.ItemReviewVOService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MysticalDream
 */
public class ItemReviewVOServiceImpl implements ItemReviewVOService {

    ItemDao itemDao = DaoFactory2.getDaoImpl(ItemDao.class);
    UserDao userDao = DaoFactory2.getDaoImpl(UserDao.class);

    @Override
    public Page<ItemReviewVO> getList(int pageSize, int pageNum) throws Exception {
        Page<ItemReviewVO> itemReviewVOPage = new Page<>(pageSize, pageNum);
        Page<Item> itemPage = new Page<>(pageSize, pageNum);
        List<Item> pendingReviewItems = itemDao.getPendingReviewItems(itemPage);
        List<ItemReviewVO> itemReviewVOList = new ArrayList<>(pendingReviewItems.size());
        for (Item item : pendingReviewItems) {
            User userById = userDao.getUserById(item.getUserId());
            ItemReviewVO itemReviewVO = new ItemReviewVO(item.getItemId(), item.getItemTitle(), item.getGmtCreate());
            itemReviewVO.setUsername(userById.getUsername());
            itemReviewVOList.add(itemReviewVO);
        }
        itemReviewVOPage.setList(itemReviewVOList);
        itemReviewVOPage.setSize(itemPage.getSize());
        itemReviewVOPage.setTotal(itemPage.getTotal());
        itemReviewVOPage.setPagesAuto();
        return itemReviewVOPage;
    }
}

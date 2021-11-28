package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.WallCloudDao;
import team.skylinekids.commonweal.factory.DaoFactory2;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.WallCloud;
import team.skylinekids.commonweal.service.WallCloudService;

/**
 * 云墙
 *
 * @author MysticalDream
 */
public class WallCloudServiceImpl implements WallCloudService {

    WallCloudDao wallCloudDao = DaoFactory2.getDaoImpl(WallCloudDao.class);

    @Override
    public int addWallCloud(WallCloud wallCloud) throws Exception {
        return wallCloudDao.addWallCloud(wallCloud);
    }

    @Override
    public void getPagination(Page<WallCloud> page,String flag) throws Exception {
        wallCloudDao.getListByPagination(page,flag);
    }
}

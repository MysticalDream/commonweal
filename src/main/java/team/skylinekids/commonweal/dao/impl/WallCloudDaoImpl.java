package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.WallCloudDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.WallCloud;

/**
 * 云墙
 *
 * @author MysticalDream
 */
public class WallCloudDaoImpl extends MyGenericBaseDao<WallCloud> implements WallCloudDao {
    @Override
    public int addWallCloud(WallCloud wallCloud) throws Exception {
        return this.insert(wallCloud);
    }

    @Override
    public void getListByPagination(Page<WallCloud> page) throws Exception {
        this.getListByPagination(" ORDER BY gmt_create DESC ", page);
    }
}

package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.WallCloud;

/**
 * 云墙
 * @author MysticalDream
 */
public interface WallCloudDao {
    /**
     * 添加云墙
     *
     * @param wallCloud
     * @return
     * @throws Exception
     */
    int addWallCloud(WallCloud wallCloud) throws Exception;

    /**
     * 分页获取
     *
     * @param page
     * @throws Exception
     */
    void getListByPagination(Page<WallCloud> page) throws Exception;


}

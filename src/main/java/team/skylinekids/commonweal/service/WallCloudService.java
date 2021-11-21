package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.WallCloud;

/**
 * 云墙服务
 *
 * @author MysticalDream
 */
public interface WallCloudService {
    /**
     * 添加云墙
     *
     * @param wallCloud
     * @return
     * @throws Exception
     */
    int addWallCloud(WallCloud wallCloud) throws Exception;

    /**
     * 获取云墙分页
     *
     * @param page
     * @throws Exception
     */
    void getPagination(Page<WallCloud> page) throws Exception;
}

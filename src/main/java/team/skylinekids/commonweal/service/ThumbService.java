package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.Thumb;

/**
 * 点赞服务
 *b
 * @author MysticalDream
 */
public interface ThumbService {
    /**
     * 处理点赞
     *
     * @param thumb
     * @throws Exception
     */
    void handleThumb(Thumb thumb) throws Exception;
}

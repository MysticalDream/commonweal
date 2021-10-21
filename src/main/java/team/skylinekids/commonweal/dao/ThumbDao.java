package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.Thumb;

/**
 * 点赞
 *
 * @author MysticalDream
 */
public interface ThumbDao {
    /**
     * 点击点赞
     *
     * @param thumb
     * @return
     * @throws Exception
     */
    boolean clickLike(Thumb thumb) throws Exception;
}

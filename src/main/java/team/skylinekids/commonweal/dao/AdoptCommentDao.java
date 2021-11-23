package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.AdoptComment;

import java.util.List;

/**
 * 领养评论
 *
 * @author MysticalDream
 */
public interface AdoptCommentDao {
    /**
     * 添加评论
     *
     * @param adoptComment
     * @return
     * @throws Exception
     */
    int addAdoptComment(AdoptComment adoptComment) throws Exception;

    /**
     * 根据领养id获取评论列表
     *
     * @param adoptId
     * @param page
     * @return
     * @throws Exception
     */
    List<AdoptComment> getAdoptCommentListByAdoptId(Integer adoptId, Page<AdoptComment> page) throws Exception;
}

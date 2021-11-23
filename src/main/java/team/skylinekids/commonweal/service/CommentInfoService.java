package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.bo.CommentInfo;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.AdoptComment;
import team.skylinekids.commonweal.pojo.po.FeedbackComment;

/**
 * 评论服务
 *
 * @author MysticalDream
 */
public interface CommentInfoService {
    /**
     * 添加领养评论
     *
     * @param adoptComment
     * @return
     * @throws Exception
     */
    int addAdoptComment(AdoptComment adoptComment) throws Exception;

    /**
     * 添加反馈评论
     *
     * @param feedbackComment
     * @return
     * @throws Exception
     */
    int addFeedBackComment(FeedbackComment feedbackComment) throws Exception;

    /**
     * 获取领养评论
     *
     * @param id
     * @param pageSize
     * @param pageNum
     * @return
     * @throws Exception
     */
    Page<CommentInfo> getAdoptCommentByAdoptId(Integer id, int pageSize, int pageNum) throws Exception;

    /**
     * 获取反馈评论
     *
     * @param id
     * @param pageSize
     * @param pageNum
     *
     * @return
     * @throws Exception
     */
    Page<CommentInfo> getFeedbackCommentByFeedbackId(Integer id, int pageSize, int pageNum) throws Exception;

}

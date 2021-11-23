package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FeedbackComment;

import java.util.List;

/**
 * @author MysticalDream
 */
public interface FeedbackCommentDao {
    /**
     * 添加反馈评论
     *
     * @param feedbackComment
     * @return
     * @throws Exception
     */
    int addFeedbackComment(FeedbackComment feedbackComment) throws Exception;

    /**
     * 根据反馈id获取评论列表
     *
     * @param feedbackId
     * @param page
     * @return
     * @throws Exception
     */
    List<FeedbackComment> getFeedbackCommentListByFeedbackId(Integer feedbackId, Page<FeedbackComment> page) throws Exception;
}

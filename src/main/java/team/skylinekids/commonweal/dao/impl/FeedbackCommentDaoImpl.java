package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.FeedbackCommentDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.po.FeedbackComment;

import java.util.List;

/**
 * @author MysticalDream
 */
public class FeedbackCommentDaoImpl extends MyGenericBaseDao<FeedbackComment> implements FeedbackCommentDao {
    @Override
    public int addFeedbackComment(FeedbackComment feedbackComment) throws Exception {
        return this.insert(feedbackComment);
    }

    @Override
    public List<FeedbackComment> getFeedbackCommentListByFeedbackId(Integer feedbackId, Page<FeedbackComment> page) throws Exception {
        List<FeedbackComment> listByPagination = this.getListByPagination(" WHERE feedback_id=" + feedbackId + " ORDER BY is_top DESC,gmt_create ", page);
        return listByPagination;
    }
}

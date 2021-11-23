package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.FeedbackResourceDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.pojo.po.FeedbackResource;

import java.util.List;

/**
 * @author MysticalDream
 */
public class FeedbackResourceDaoImpl extends MyGenericBaseDao<FeedbackResource> implements FeedbackResourceDao {
    @Override
    public int addResourceDao(FeedbackResource... feedbackResources) throws Exception {
        for (FeedbackResource feedbackResource :
                feedbackResources) {
            this.insert(feedbackResource);
        }
        return feedbackResources.length;
    }

    @Override
    public List<FeedbackResource> getFeedbackResourcesByFeedbackCommentId(Integer feedbackCommentId) throws Exception {
        FeedbackResource feedbackResource = new FeedbackResource();
        feedbackResource.setFeedbackId(feedbackCommentId);
        return this.selectList(feedbackResource);
    }
}

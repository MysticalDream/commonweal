package team.skylinekids.commonweal.dao.impl;

import team.skylinekids.commonweal.dao.FeedbackResourceDao;
import team.skylinekids.commonweal.dao.core.MyGenericBaseDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.pojo.po.FeedbackResource;

import java.util.List;

/**
 * @author MysticalDream
 */
public class FeedbackResourceDaoImpl extends MyGenericBaseDao<FeedbackResource> implements FeedbackResourceDao {
    @Override
    public int addResource(FeedbackResource... feedbackResources) throws Exception {
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
        List<FeedbackResource> resourceList = this.selectList(feedbackResource);
        for (FeedbackResource feedbackResource1 :
                resourceList) {
            feedbackResource1.setSrcName(ResourcePathConstant.VIRTUAL_FEEDBACK_BASE + feedbackResource1.getSrcName());
        }
        return resourceList;
    }
}

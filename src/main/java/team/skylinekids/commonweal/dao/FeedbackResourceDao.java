package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.FeedbackResource;

import java.util.List;

/**
 * 反馈资源dao
 *
 * @author MysticalDream
 */
public interface FeedbackResourceDao {

    /**
     * 添加资源
     *
     * @param feedbackResources
     * @return
     * @throws Exception
     */
    int addResource(FeedbackResource... feedbackResources) throws Exception;

    /**
     * 根据反馈id获取反馈图片资源
     *
     * @param FeedbackCommentId
     * @return
     * @throws Exception
     */
    List<FeedbackResource> getFeedbackResourcesByFeedbackCommentId(Integer FeedbackCommentId) throws Exception;

}

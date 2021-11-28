package team.skylinekids.commonweal.web.controller.adopt;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.bo.CommentInfo;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.FeedbackDTO;
import team.skylinekids.commonweal.pojo.po.Adopt;
import team.skylinekids.commonweal.pojo.po.AdoptComment;
import team.skylinekids.commonweal.pojo.po.FeedbackComment;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.AdoptService;
import team.skylinekids.commonweal.service.CommentInfoService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 领养动物反馈
 *
 * @author MysticalDream
 */
public class AdoptFeedBackController {

    CommentInfoService commentInfoService = ServiceFactory2.getServiceImplProxy(CommentInfoService.class);
    AdoptService adoptService = ServiceFactory2.getServiceImplProxy(AdoptService.class);
    Logger logger = Logger.getLogger(AdoptFeedBackController.class);


    /**
     * 添加反馈
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/feedback", type = {RequestMethod.POST})
    @AccessLevel
    public String addFeedBack(HttpInfoWrapper httpInfoWrapper) throws Exception {
        User user = httpInfoWrapper.getUser();
        FeedbackDTO feedbackDTO = GsonUtils.j2O(httpInfoWrapper.getJsonString(), FeedbackDTO.class);
        int adoptId = feedbackDTO.getAdoptId();
        String[] pics = feedbackDTO.getPics();
        for (int i = 0; i < pics.length; i++) {
            String fileName = FileUtils.getFileName(pics[i]);
            //把封面从暂存区放到真正的目录中
            FileUtils.cutFile(ResourcePathConstant.DISK_FEEDBACK_TEMP_BASE + fileName, ResourcePathConstant.DISK_FEEDBACK_IMG_BASE + fileName);
            pics[i] = fileName;
        }
        AdoptComment adoptComment = new AdoptComment();
        adoptComment.setContent(feedbackDTO.getContent());
        adoptComment.setAdoptId(feedbackDTO.getAdoptId());
        adoptComment.setUserId(user.getUserId());
        Adopt adoptById = adoptService.getAdoptById(adoptId);
        if (adoptById.getAdoptUserId().equals(user.getUserId())) {
            adoptComment.setTop(1);
        }
        commentInfoService.addAdoptComment(adoptComment, pics);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 添加反馈评论
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/feedback/comment", type = {RequestMethod.POST})
    @AccessLevel
    public String addFeedBackComment(HttpInfoWrapper httpInfoWrapper) throws Exception {
        FeedbackComment feedbackComment = GsonUtils.j2O(httpInfoWrapper.getJsonString(), FeedbackComment.class);
        User user = httpInfoWrapper.getUser();
        feedbackComment.setUserId(user.getUserId());
        commentInfoService.addFeedBackComment(feedbackComment);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 分页获取领养评论的评论
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/feedback/comment", type = {RequestMethod.GET})
    public String getFeedbackComment(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer id;
        Integer pageSize;
        Integer pageNum;
        try {
            id = httpInfoWrapper.getParameter("id", Integer.class);
            pageSize = httpInfoWrapper.getParameter("pageSize", Integer.class);
            pageNum = httpInfoWrapper.getParameter("pageNum", Integer.class);
        } catch (ClassCastException e) {
            logger.info("领养评论参数解析异常", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<CommentInfo> feedbackCommentByFeedbackId = commentInfoService.getFeedbackCommentByFeedbackId(id, pageSize, pageNum);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, feedbackCommentByFeedbackId);
    }


}

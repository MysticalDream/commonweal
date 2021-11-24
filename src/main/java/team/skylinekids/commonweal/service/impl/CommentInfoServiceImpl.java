package team.skylinekids.commonweal.service.impl;

import team.skylinekids.commonweal.dao.AdoptCommentDao;
import team.skylinekids.commonweal.dao.FeedbackCommentDao;
import team.skylinekids.commonweal.dao.FeedbackResourceDao;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.DaoFactory2;
import team.skylinekids.commonweal.pojo.bo.CommentInfo;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.AdoptComment;
import team.skylinekids.commonweal.pojo.po.FeedbackComment;
import team.skylinekids.commonweal.pojo.po.FeedbackResource;
import team.skylinekids.commonweal.service.CommentInfoService;
import team.skylinekids.commonweal.utils.ResourceURLUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论服务
 *
 * @author MysticalDream
 */
public class CommentInfoServiceImpl implements CommentInfoService {

    AdoptCommentDao adoptCommentDao = DaoFactory2.getDaoImpl(AdoptCommentDao.class);

    FeedbackCommentDao feedbackCommentDao = DaoFactory2.getDaoImpl(FeedbackCommentDao.class);

    UserDao userDao = DaoFactory2.getDaoImpl(UserDao.class);

    FeedbackResourceDao feedbackResourceDao = DaoFactory2.getDaoImpl(FeedbackResourceDao.class);

    @Override
    public int addAdoptComment(AdoptComment adoptComment) throws Exception {
        return adoptCommentDao.addAdoptComment(adoptComment);
    }

    @Override
    public int addAdoptComment(AdoptComment adoptComment, String[] feedbackResources) throws Exception {
        this.addAdoptComment(adoptComment);
        Integer id = adoptComment.getId();
        if (feedbackResources != null) {
            FeedbackResource[] arr = new FeedbackResource[feedbackResources.length];
            for (int i = 0; i < feedbackResources.length; i++) {
                arr[i] = new FeedbackResource();
                arr[i].setSrcName(feedbackResources[i]);
                arr[i].setFeedbackId(id);
            }
            this.addResource(arr);
        }
        return 1;
    }

    @Override
    public int addFeedBackComment(FeedbackComment feedbackComment) throws Exception {
        return feedbackCommentDao.addFeedbackComment(feedbackComment);
    }

    @Override
    public Page<CommentInfo> getAdoptCommentByAdoptId(Integer id, int pageSize, int pageNum) throws Exception {
        Page<AdoptComment> page = new Page<>(pageSize, pageNum);
        Page<CommentInfo> page1 = new Page<>(pageSize, pageNum);
        List<AdoptComment> adoptCommentListByAdoptId = adoptCommentDao.getAdoptCommentListByAdoptId(id, page);

        List<CommentInfo> commentInfoList = new ArrayList<>(adoptCommentListByAdoptId.size());
        for (AdoptComment adoptComment : adoptCommentListByAdoptId) {
            Integer userId = adoptComment.getUserId();
            UserDTO userDTO = ConversionUtils.convert(userDao.getUserById(userId), UserDTO.class);
            userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());
            CommentInfo commentInfo = new CommentInfo(adoptComment, userDTO);
            if (adoptComment.getTop().equals(1)) {
                commentInfo.setPicList(feedbackResourceDao.getFeedbackResourcesByFeedbackCommentId(adoptComment.getId()));
            }
            commentInfoList.add(commentInfo);
        }

        page1.setList(commentInfoList);
        page1.setTotal(page.getTotal());
        page1.setPagesAuto();
        page1.setSize(page.getSize());
        return page1;
    }

    @Override
    public Page<CommentInfo> getFeedbackCommentByFeedbackId(Integer id, int pageSize, int pageNum) throws Exception {
        Page<FeedbackComment> page = new Page<>(pageSize, pageNum);
        Page<CommentInfo> page1 = new Page<>(pageSize, pageNum);
        List<FeedbackComment> list = feedbackCommentDao.getFeedbackCommentListByFeedbackId(id, page);
        List<CommentInfo> commentInfoList = new ArrayList<>(list.size());
        for (FeedbackComment feedbackComment : list) {
            Integer userId = feedbackComment.getUserId();
            UserDTO userDTO = ConversionUtils.convert(userDao.getUserById(userId), UserDTO.class);
            userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());
            CommentInfo commentInfo = new CommentInfo(feedbackComment, userDTO);
            commentInfoList.add(commentInfo);
        }
        page1.setList(commentInfoList);
        page1.setTotal(page.getTotal());
        page1.setPagesAuto();
        page1.setSize(page.getSize());
        return page1;
    }

    @Override
    public int addResource(FeedbackResource... feedbackResources) throws Exception {
        return feedbackResourceDao.addResource(feedbackResources);
    }

    @Override
    public List<FeedbackResource> getFeedbackResourcesByFeedbackCommentId(Integer feedbackCommentId) throws Exception {
        return feedbackResourceDao.getFeedbackResourcesByFeedbackCommentId(feedbackCommentId);
    }
}

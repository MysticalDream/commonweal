package team.skylinekids.commonweal.web.controller.adopt;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.bo.CommentInfo;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.FeedbackDTO;
import team.skylinekids.commonweal.pojo.po.Adopt;
import team.skylinekids.commonweal.pojo.po.AdoptComment;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.pojo.po.UserInfo;
import team.skylinekids.commonweal.service.AdoptService;
import team.skylinekids.commonweal.service.CommentInfoService;
import team.skylinekids.commonweal.service.UserInfoService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.util.Map;

/**
 * 领养动物模块
 *
 * @author MysticalDream
 */
public class AdoptController {

    private final Logger logger = Logger.getLogger(AdoptController.class);

    AdoptService adoptService = ServiceFactory.getAdoptService();

    UserInfoService userInfoService = ServiceFactory2.getServiceImplProxy(UserInfoService.class);

    CommentInfoService commentInfoService = ServiceFactory2.getServiceImplProxy(CommentInfoService.class);

    /**
     * 封面上传接口
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/adopt/cover", type = {RequestMethod.POST})
    public String uploadAdoptCover(HttpInfoWrapper httpInfoWrapper) {

        return "未开发";
    }

    /**
     * 用户领养动物
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/adopt/user", type = {RequestMethod.POST})
    public String adoptAnimal(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Boolean status = (Boolean) httpInfoWrapper.getHttpSessionAttribute(SessionKeyConstant.AGREEMENT_KEY);
        if (status == null || !status) {
            return ResultUtils.getResult(ApiResultCode.REJECT_THE_REQUEST);
        }
        Map<String, String[]> parameterMap = httpInfoWrapper.getParameterMap();
        int adoptId = Integer.parseInt(parameterMap.remove("adoptId")[0]);
        UserInfo userInfo = FillBeanUtils.fill(parameterMap, UserInfo.class);
        Integer userId = httpInfoWrapper.getUser().getUserId();
        userInfo.setUserId(userId);
        userInfoService.addUserInfo(userInfo);
        adoptService.updateAdoptUserId(new Adopt(adoptId, userId));
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 获取用户领养的动物
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/adopt/user", type = {RequestMethod.GET})
    @AccessLevel
    public String getUserAdopt(HttpInfoWrapper httpInfoWrapper) throws Exception {
        User user = httpInfoWrapper.getUser();
        Integer pageSize;
        Integer pageNum;
        try {
            pageSize = httpInfoWrapper.getParameter("pageSize", Integer.class);
            pageNum = httpInfoWrapper.getParameter("pageNum", Integer.class);
        } catch (ClassCastException e) {
            logger.info("前端参数解析异常", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<Adopt> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        adoptService.getUserAdopt(user.getUserId(), page);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, page);
    }

    /**
     * 签订协议
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/adopt/sign", type = {RequestMethod.POST})
    public String signAgreement(HttpInfoWrapper httpInfoWrapper) {
        boolean status;
        try {
            status = Boolean.valueOf(httpInfoWrapper.getParameter("status"));
        } catch (Exception e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        httpInfoWrapper.setHttpSessionAttribute(SessionKeyConstant.AGREEMENT_KEY, status);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 添加领养动物信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/adopt", type = {RequestMethod.POST})
    public String addAdoptInfo(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Adopt adopt = GsonUtils.j2O(httpInfoWrapper.getJsonString(), Adopt.class);
        if (adopt == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        String fileName = FileUtils.getFileName(adopt.getCoverUrl());
        //把封面从暂存区放到真正的目录中
        if (!FileUtils.cutFile(ResourcePathConstant.DISK_ADOPT_TEMP_IMG_BASE + fileName, ResourcePathConstant.DISk_ADOPT_IMG_BASE + fileName)) {
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
        adopt.setCoverUrl(fileName);
        adoptService.addAdoptInfo(adopt);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 获取领养动物的列表
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/adopts", type = {RequestMethod.GET})
    public String getAdoptList(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pageSize;
        Integer pageNum;
        String option = httpInfoWrapper.getParameter("option");
        try {
            pageSize = Integer.parseInt(httpInfoWrapper.getParameter("pageSize"));
            pageNum = Integer.parseInt(httpInfoWrapper.getParameter("pageNum"));
        } catch (Exception e) {
            logger.error("领养请求分页语法错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<Adopt> page = new Page<>();
        page.setPageNum(pageNum);
        Page<Adopt> adoptList;
        page.setPageSize(pageSize);

        if ("true".equals(option)) {
            adoptList = adoptService.getAdoptList(page, true);
        } else {
            adoptList = adoptService.getAdoptList(page, false);
        }
        return ResultUtils.getResult(ApiResultCode.SUCCESS, adoptList);
    }

    /**
     * 添加领养评论
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/adopt/feedback", type = {RequestMethod.POST})
    public String addAdoptComment(HttpInfoWrapper httpInfoWrapper) throws Exception {
        User user = httpInfoWrapper.getUser();
        FeedbackDTO feedbackDTO = GsonUtils.j2O(httpInfoWrapper.getJsonString(), FeedbackDTO.class);
        int adoptId = feedbackDTO.getAdoptId();
        String[] pics = feedbackDTO.getPics();
        if (pics != null) {
            for (int i = 0; i < pics.length; i++) {
                String fileName = FileUtils.getFileName(pics[i]);
                //把封面从暂存区放到真正的目录中
                FileUtils.cutFile(ResourcePathConstant.DISK_FEEDBACK_TEMP_BASE + fileName, ResourcePathConstant.DISK_FEEDBACK_IMG_BASE + fileName);
                pics[i] = fileName;
            }
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
     * 获取领养评论
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/adopt/feedback", type = {RequestMethod.GET})
    public String getAdoptComment(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer adoptId;
        Integer pageSize;
        Integer pageNum;
        try {
            adoptId = httpInfoWrapper.getParameter("adoptId", Integer.class);
            pageSize = httpInfoWrapper.getParameter("pageSize", Integer.class);
            pageNum = httpInfoWrapper.getParameter("pageNum", Integer.class);
        } catch (ClassCastException e) {
            logger.info("领养评论参数解析异常", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<CommentInfo> adoptCommentByAdoptId = commentInfoService.getAdoptCommentByAdoptId(adoptId, pageSize, pageNum);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, adoptCommentByAdoptId);
    }

    /**
     * 获取动物信息
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/adopt", type = {RequestMethod.GET})
    public String getAdoptDetail(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer adoptId = httpInfoWrapper.getParameter("adoptId", Integer.class);
        Adopt adoptById = adoptService.getAdoptById(adoptId);
        adoptById.setCoverUrl(ResourcePathConstant.VIRTUAL_ADOPT_COVER_BASE + adoptById.getCoverUrl());
        return ResultUtils.getResult(ApiResultCode.SUCCESS, adoptById);
    }
}

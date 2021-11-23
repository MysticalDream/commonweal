package team.skylinekids.commonweal.web.controller.adopt;

import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.service.CommentInfoService;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.util.Map;

/**
 * 领养动物反馈
 *
 * @author MysticalDream
 */
public class AdoptFeedBackController {

    CommentInfoService commentInfoService = ServiceFactory2.getServiceImplProxy(CommentInfoService.class);

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
        Map<String, String[]> parameterMap = httpInfoWrapper.getParameterMap();
        return "";
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
        return "";
    }


}

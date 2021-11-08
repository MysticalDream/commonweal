package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.po.Thumb;
import team.skylinekids.commonweal.service.ThumbService;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 点赞
 *
 * @author MysticalDream
 */
public class ThumbController {

    ThumbService thumbService = ServiceFactory.getThumbService();

    /**
     * 点赞
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel
    @MyRequestPath(value = "/thumb", type = {RequestMethod.POST})
    public String thumb(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String jsonString = httpInfoWrapper.getJsonString();
        Thumb thumb = GsonUtils.j2O(jsonString, Thumb.class);
        if (thumb == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        thumb.setUserId(httpInfoWrapper.getUser().getUserId());
        thumbService.handleThumb(thumb);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }


}

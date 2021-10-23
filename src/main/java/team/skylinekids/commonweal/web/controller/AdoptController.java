package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 领养动物模块
 *
 * @author MysticalDream
 */
public class AdoptController {
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

    @MyRequestPath(value = "/adopt", type = {RequestMethod.POST})
    public String addAdoptInfo(HttpInfoWrapper httpInfoWrapper) {

        return "";
    }

}

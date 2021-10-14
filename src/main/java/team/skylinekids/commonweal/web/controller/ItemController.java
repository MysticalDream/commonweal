package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 项目Controller
 *
 * @author MysticalDream
 */
public class ItemController {
    /**
     * 添加项目
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/items", type = {RequestMethod.POST})
    public String addItem(HttpInfoWrapper httpInfoWrapper) {

        return "添加项目";
    }

    public String getItem(HttpInfoWrapper httpInfoWrapper) {
        return "获取项目";
    }
}

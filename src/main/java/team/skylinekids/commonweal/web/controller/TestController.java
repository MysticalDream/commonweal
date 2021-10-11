package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

/**
 * 测试控制器
 *
 * @author MysticalDream
 */
public class TestController {
    @MyRequestPath("/mytest")
    public String test(HttpInfoWrapper httpInfoWrapper) {
        return "1231";
    }
}

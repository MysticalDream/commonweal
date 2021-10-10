package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestType;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.utils.ConversionUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.utils.FileUtils;

import javax.servlet.http.Part;
import java.io.IOException;


/**
 * 用户controller
 *
 * @author MysticalDream
 */
@MyRequestPath("/user")
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService = ServiceFactory.getUserService();

    @MyRequestPath(value = "/login", type = {RequestType.POST})
    public String login(HttpInfoWrapper httpWrapper) {
        User user1 = FillBeanUtils.fill(httpWrapper.getParameterMap(), User.class);
        User user2 = userService.login(user1);
        if (user2 == null) {
            return ResultUtils.getResult(ApiResultCode.PASSWORD_OR_USER_NAME_IS_INCORRECT);
        }
        return ResultUtils.getResult(ApiResultCode.SUCCESS, ConversionUtils.convert(user2, UserDTO.class));
    }

    @MyRequestPath("/register")
    public String register(HttpInfoWrapper httpWrapper) {
        return "register";
    }

    @MyRequestPath("/test")
    public String test(HttpInfoWrapper httpInfoWrapper) {
        httpInfoWrapper.getParameterMap();
        Part part = httpInfoWrapper.getPart("file");
        if (part == null) {
            return "part为空";
        }
        try {
            String s = FileUtils.saveAvatarByPart(part);
            System.out.println(s);
        } catch (IOException e) {
            logger.error("头像图片文件保存异常", e);
        }
        return "测试";
    }

}

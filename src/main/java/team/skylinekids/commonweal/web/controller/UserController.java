package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import java.io.IOException;


/**
 * 用户controller
 *
 * @author MysticalDream
 */
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService = ServiceFactory.getUserService();

    /**
     * 登录
     *
     * @param httpWrapper
     * @return
     */
    @MyRequestPath(value = "/sessions", type = {RequestMethod.POST})
    public String login(HttpInfoWrapper httpWrapper) {

        if (httpWrapper.isUserLogin()) {
            //已经登录无需登录
            return ResultUtils.getResult(ApiResultCode.REDIRECT, "/");
        }
        User user1 = FillBeanUtils.fill(httpWrapper.getParameterMap(), User.class);
        //前端提交数据的字段名称或者是字段类型和后台的实体类不一致，导致无法封装
        if (user1 == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        User user2 = userService.login(user1);
        //密码或则用户名错误
        if (user2 == null) {
            return ResultUtils.getResult(ApiResultCode.PASSWORD_OR_USER_NAME_IS_INCORRECT);
        }
        //设置登录用户到session会话
        httpWrapper.setUserToSession(user2);

        UserDTO userDTO = ConversionUtils.convert(user2, UserDTO.class);

        httpWrapper.setCookies(ConversionUtils.oToStringMap(userDTO), 86400);

        //登录成功
        return ResultUtils.getResult(ApiResultCode.SUCCESS, userDTO);
    }

    /**
     * 退出登录
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/sessions", type = {RequestMethod.DELETE})
    public String logout(HttpInfoWrapper httpInfoWrapper) {
        if (httpInfoWrapper.isUserLogin()) {
            //退出登录
            httpInfoWrapper.removeUserFromSession();
            return ResultUtils.getResult(ApiResultCode.SUCCESS);
        }
        //用户没有登录
        return ResultUtils.getResult(ApiResultCode.UNAUTHENTICATED);
    }

    /**
     * 获取用户列表
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/user", type = {RequestMethod.GET})
    public String getUserList(HttpInfoWrapper httpInfoWrapper) {
        return "getUserList";
    }

    /**
     * 注册
     *
     * @param httpWrapper
     * @return
     */
    @MyRequestPath(value = "/user", type = {RequestMethod.POST})
    public String register(HttpInfoWrapper httpWrapper) {

        User user1 = FillBeanUtils.fill(httpWrapper.getParameterMap(), User.class);
        //前端提交数据的字段名称或者是字段类型和后台的实体类不一致，导致无法封装
        if (user1 == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }

        return "register";
    }

    /**
     * 获取用户信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/user/?", type = {RequestMethod.GET})
    public String getUseInfoById(HttpInfoWrapper httpInfoWrapper) {
        return "getUseInfoById";
    }

    /**
     * 更新用户信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/user/?", type = {RequestMethod.PUT})
    public String updateUseInfoById(HttpInfoWrapper httpInfoWrapper) {
        return "updateUseInfoById";
    }

    /**
     * 删除用户
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/user/?", type = {RequestMethod.DELETE})
    public String deleteUserById(HttpInfoWrapper httpInfoWrapper) {
        return "deleteUserById";
    }

    @MyRequestPath("/testD")
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

    @MyRequestPath(value = "/test", type = {RequestMethod.POST})
    public String test1(HttpInfoWrapper httpInfoWrapper) {
        return "test1";
    }

    @MyRequestPath(value = "/test/?", type = {RequestMethod.GET})
    public String test2(HttpInfoWrapper httpInfoWrapper) {
        //向前端页面返回路径参数值
        return httpInfoWrapper.getPathVariable(Integer.class) + "";
    }

    @MyRequestPath(value = "/?", type = {RequestMethod.GET})
    public String test3(HttpInfoWrapper httpInfoWrapper) {
        Cookie cookie = httpInfoWrapper.getCookie("location");
        if (cookie != null) {
            System.out.println(cookie.getValue());
        }
        return httpInfoWrapper.getPathVariable(Integer.class) + "";
    }

}

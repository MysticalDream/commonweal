package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.utils.*;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

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
    public String login(HttpInfoWrapper httpWrapper) throws Exception {

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
    @MyRequestPath(value = "/users", type = {RequestMethod.GET})
    public String getUserList(HttpInfoWrapper httpInfoWrapper) {
        return "getUserList";
    }

    /**
     * 注册
     * 不幂等
     *
     * @param httpWrapper
     * @return
     */
    @MyRequestPath(value = "/users", type = {RequestMethod.POST})
    public String register(HttpInfoWrapper httpWrapper) throws Exception {

        Cookie cookie = httpWrapper.getCookie(SessionKeyConstant.SIGNUP_TOKEN_STRING);
        //客户端拿不到令牌值
        if (cookie == null) {
            //移除令牌
            removeToken(httpWrapper);
            return ResultUtils.getResult(ApiResultCode.THE_TOKEN_ERROR);
        }
        String clientToken = cookie.getValue();
        String sessionToken = (String) httpWrapper.getHttpSessionAttribute(SessionKeyConstant.SIGNUP_TOKEN_STRING);
        //令牌失效
        if (sessionToken == null) {
            //移除令牌
            removeToken(httpWrapper);
            return ResultUtils.getResult(ApiResultCode.THE_TOKEN_IS_INVALID);
        }
        //客户端令牌不正确
        if (!sessionToken.equals(clientToken)) {
            //移除令牌
            removeToken(httpWrapper);
            return ResultUtils.getResult(ApiResultCode.THE_TOKEN_ERROR);
        }

        User user1 = FillBeanUtils.fill(httpWrapper.getParameterMap(), User.class);
        //前端提交数据的字段名称或者是字段类型和后台的实体类不一致，导致无法封装
        if (user1 == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //TODO 注册信息验证
        userService.register(user1);
        //移除令牌
        removeToken(httpWrapper);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 移除令牌
     *
     * @param httpInfoWrapper
     */
    private void removeToken(HttpInfoWrapper httpInfoWrapper) {
        httpInfoWrapper.removeHttpSessionAttribute(SessionKeyConstant.SIGNUP_TOKEN_STRING);
        httpInfoWrapper.setCookie(SessionKeyConstant.SIGNUP_TOKEN_STRING, "", 0);
    }

    /**
     * 获取用户信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/users/?", type = {RequestMethod.GET})
    public String getUseInfoById(HttpInfoWrapper httpInfoWrapper) {
        return "getUseInfoById";
    }

    /**
     * 更新用户信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/users/?", type = {RequestMethod.PUT})
    public String updateUseInfoById(HttpInfoWrapper httpInfoWrapper) {
        return "updateUseInfoById";
    }

    /**
     * 删除用户
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/users/?", type = {RequestMethod.DELETE})
    public String deleteUserById(HttpInfoWrapper httpInfoWrapper) {
        return "deleteUserById";
    }

    /**
     * 获取token
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/tokens/signup", type = {RequestMethod.GET})
    public String getSignUpToken(HttpInfoWrapper httpInfoWrapper) {
        String token = TokenUtils.getToken();
        httpInfoWrapper.setCookie(SessionKeyConstant.SIGNUP_TOKEN_STRING, token, "/");
        httpInfoWrapper.setHttpSessionAttribute(SessionKeyConstant.SIGNUP_TOKEN_STRING, token);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, token);
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


}

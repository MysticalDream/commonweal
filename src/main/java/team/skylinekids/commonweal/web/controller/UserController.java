package team.skylinekids.commonweal.web.controller;

import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.*;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.TokenUtils;
import team.skylinekids.commonweal.utils.VerifyUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Cookie;
import java.util.Map;


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
        if (httpWrapper.isLogin()) {
            //已经登录无需登录
//            httpWrapper.getHttpServletResponse().sendRedirect("/");
            return ResultUtils.getResult(ApiResultCode.REDIRECT, "/");
        }
        //用户名
        String username = httpWrapper.getParameter("username");
        //密码
        String password = httpWrapper.getParameter("password");
        if (username == null || password == null) {
            return ResultUtils.getResult(ApiResultCode.PASSWORD_OR_USER_NAME_IS_INCORRECT);
        }
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
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
        //拼接头像路径
        userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());

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
    @AccessLevel
    @MyRequestPath(value = "/sessions", type = {RequestMethod.DELETE})
    public String logout(HttpInfoWrapper httpInfoWrapper) {
        //清除Cookie
        httpInfoWrapper.setCookies(ConversionUtils.oToStringMap(new UserDTO()), 0);
        //退出登录
        httpInfoWrapper.removeUserFromSession();
        logger.info("退出登录");
        return ResultUtils.getResult(ApiResultCode.SUCCESS);

    }

    /**
     * 获取用户列表
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel(LevelCode.SPECIAL_LOGIN_LEVEL)
    @MyRequestPath(value = "/users", type = {RequestMethod.GET})
    public String getUserList(HttpInfoWrapper httpInfoWrapper) {
        return "getUserList";
    }

    /**
     * 注册(先请求Token)
     * 不幂等
     *
     * @param httpWrapper
     * @return
     * @see #getSignUpToken
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
        //客户端令牌
        String clientToken = cookie.getValue();
        //服务端令牌
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
        //获取注册信息
        Map<String, String[]> signupMap = httpWrapper.getParameterMap();
        //用户名
        String username = (signupMap.get("username"))[0];
        //密码1
        String password1 = (signupMap.get("password1"))[0];
        //密码2
        String password2 = (signupMap.get("password2"))[0];

        //用户信息验证
        if (!VerifyUtils.verifyUsername(username)) {
            return ResultUtils.getResult(ApiResultCode.INCORRECT_USERNAME_FORMAT);
        }
        //密码不一致
        if (!password1.equals(password2)) {
            return ResultUtils.getResult(ApiResultCode.USER_PASSWORD_NOT_EQUAL);
        }
        //密码格式不正确
        if (!VerifyUtils.verifyPassword(password1)) {
            return ResultUtils.getResult(ApiResultCode.INCORRECT_PASSWORD_FORMAT);
        }
        //添加password属性
        signupMap.put("password", signupMap.remove("password1"));
        signupMap.remove("password2");
        User user1 = FillBeanUtils.fill(signupMap, User.class);

        //前端提交数据的字段名称或者是字段类型和后台的实体类不一致，导致无法封装
        if (user1 == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            if (userService.register(user1) == 0) {
                return ResultUtils.getResult(ApiResultCode.USER_NAME_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            //移除令牌
            removeToken(httpWrapper);
            throw e;
        }
        removeToken(httpWrapper);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, ConversionUtils.convert(user1, UserDTO.class));
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
     * 获取用户信息根据id
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel(LevelCode.SPECIAL_LOGIN_LEVEL)
    @MyRequestPath(value = "/users/?", type = {RequestMethod.GET})
    public String getUseInfoById(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pathVariable;
        try {
            pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (JsonSyntaxException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        User userById = userService.getUserById(pathVariable);
        if (userById == null) {
            return ResultUtils.getResult(ApiResultCode.UNKNOWN_USER);
        }
        return ResultUtils.getResult(ApiResultCode.SUCCESS, ConversionUtils.convert(userById, UserDTO.class));
    }

    /**
     * 更新用户信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
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
    @AccessLevel(LevelCode.SPECIAL_LOGIN_LEVEL)
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
        //TODO 删除了设置路径
        httpInfoWrapper.setCookie(SessionKeyConstant.SIGNUP_TOKEN_STRING, token);
        httpInfoWrapper.setHttpSessionAttribute(SessionKeyConstant.SIGNUP_TOKEN_STRING, token);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, token);
    }

}

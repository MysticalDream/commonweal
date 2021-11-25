package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.LevelCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.pojo.vo.ItemReviewVO;
import team.skylinekids.commonweal.service.ItemReviewVOService;
import team.skylinekids.commonweal.service.ItemService;
import team.skylinekids.commonweal.service.UserService;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;


/**
 * 管理员控制器
 *
 * @author MysticalDream
 */
public class AdminController {

    UserService userService = ServiceFactory2.getServiceImplProxy(UserService.class);
    ItemService itemService = ServiceFactory2.getServiceImplProxy(ItemService.class);
    ItemReviewVOService itemReviewVOService = ServiceFactory2.getServiceImplProxy(ItemReviewVOService.class);

    private Logger logger = Logger.getLogger(AdminController.class);

    /**
     * 管理员登录
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/admin/sessions", type = {RequestMethod.POST})
    public String adminLogin(HttpInfoWrapper httpInfoWrapper) throws Exception {
        if (httpInfoWrapper.isLogin()) {
            return ResultUtils.getResult(ApiResultCode.REDIRECT, "/");
        }
        //用户名
        String username = httpInfoWrapper.getParameter("username");
        //密码
        String password = httpInfoWrapper.getParameter("password");
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
        httpInfoWrapper.setUserToSession(user2);

        UserDTO userDTO = ConversionUtils.convert(user2, UserDTO.class);
        //拼接头像路径
        userDTO.setAvatarUrl(ResourcePathConstant.VIRTUAL_USER_AVATAR_URL_BASE + userDTO.getAvatarUrl());

        httpInfoWrapper.setCookies(ConversionUtils.oToStringMap(userDTO), 86400);
        httpInfoWrapper.getHttpServletResponse().sendRedirect("/pages/backStage/backStage.html");
        //登录成功
        return ResultUtils.getResult(ApiResultCode.SUCCESS, userDTO);
    }

    /**
     * 审核项目(简单实现，没有记录操作人和一系列严格的验证)
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/admin/items/check")
    @AccessLevel(LevelCode.COMMON_ADMIN_LEVEL)
    public String checkItem(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String option;
        Item item = new Item();
        try {
            option = httpInfoWrapper.getParameter("option");
            int itemId = Integer.parseInt(httpInfoWrapper.getParameter("itemId"));
            item.setItemId(itemId);
        } catch (Exception e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        if ("true".equals(option)) {
            item.setCheckStatus(1);
            itemService.checkItem(item);
        } else if ("false".equals(option)) {
            item.setCheckStatus(2);
            itemService.checkItem(item);
        } else {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 获取待审核的项目
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/admin/item", type = {RequestMethod.GET})
    @AccessLevel(LevelCode.COMMON_ADMIN_LEVEL)
    public String getItemCheckList(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pageSize;
        Integer pageNum;
        try {
            pageSize = Integer.parseInt(httpInfoWrapper.getParameter("pageSize"));
            pageNum = Integer.parseInt(httpInfoWrapper.getParameter("pageNum"));
        } catch (Exception e) {
            logger.error("管理：项目请求分页语法错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Page<ItemReviewVO> list = itemReviewVOService.getList(pageSize, pageNum);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, list);
    }

    /**
     * @param httpInfoWrapper
     * @throws Exception
     */
    @MyRequestPath(value = "/admin", type = {RequestMethod.GET})
    public void reDirect(HttpInfoWrapper httpInfoWrapper) throws Exception {
        httpInfoWrapper.getHttpServletResponse().sendRedirect("/pages/backStage/managerLogin.html");
    }
}

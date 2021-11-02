package team.skylinekids.commonweal.web.controller;

import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.LevelCode;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.bo.ItemBO;
import team.skylinekids.commonweal.pojo.bo.Page;
import team.skylinekids.commonweal.pojo.dto.ItemDTO;
import team.skylinekids.commonweal.pojo.po.Item;
import team.skylinekids.commonweal.pojo.po.ItemMemberMap;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.pojo.vo.ItemMemberVO;
import team.skylinekids.commonweal.service.ItemBOService;
import team.skylinekids.commonweal.service.ItemMemberMapService;
import team.skylinekids.commonweal.service.ItemService;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.FillBeanUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 项目Controller
 *
 * @author MysticalDream
 */
public class ItemController {

    private final Logger logger = Logger.getLogger(ItemController.class);

    ItemService itemService = ServiceFactory.getItemService();

    ItemMemberMapService itemMemberMapService = ServiceFactory.getItemMemberMapService();

    ItemBOService itemBOService = ServiceFactory.getItemBOService();

    /**
     * 添加项目
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/items", type = {RequestMethod.POST})
    public String createItem(HttpInfoWrapper httpInfoWrapper) throws Exception {

        ItemDTO itemDTO = GsonUtils.j2O(httpInfoWrapper.getJsonString(), ItemDTO.class);

        if (itemDTO == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        String tempCoverUrl = itemDTO.getCoverUrl();
        //文件名
        String fileName = FileUtils.getFileName(tempCoverUrl);
        //把封面从暂存区放到真正的目录中
        if (!FileUtils.cutFile(ResourcePathConstant.DISK_ITEM_COVER_TEMP_BASE_URL + fileName, ResourcePathConstant.DISK_ITEM_COVER_BASE_URL + fileName)) {
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }

        Item item = ConversionUtils.convert(itemDTO, Item.class);
        //分类
        item.setItemCategoryId(CategoryUtils.getCategoryIdByName(itemDTO.getItemCategory()));
        //封面文件名
        item.setCoverUrl(fileName);
        //项目创建者用户id
        item.setUserId(httpInfoWrapper.getUser().getUserId());
        itemService.createItem(item);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 根据条件获取
     *
     * @param httpInfoWrapper
     * @return
     * @throws IOException
     */
    @MyRequestPath(value = "/items/conditions", type = {RequestMethod.GET})
    public String getItemsByConditionPage(HttpInfoWrapper httpInfoWrapper) throws Exception {
        //项目查询条件
        // ItemCondition itemCondition = GsonUtils.j2O(httpInfoWrapper.getParameter("json"), ItemCondition.class);
        ItemCondition itemCondition = FillBeanUtils.fill(httpInfoWrapper.getParameterMap(), ItemCondition.class);
        if (itemCondition == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //项目分类id设置
        itemCondition.setItemCategoryId(CategoryUtils.getCategoryIdByName(itemCondition.getItemCategory()));
        //根据条件获取项目数据
        Page<ItemDTO> items = itemService.getItemByCondition(itemCondition);

        return ResultUtils.getResult(ApiResultCode.SUCCESS, items);
    }

    /**
     * 根据项目id获取项目信息
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/items/?", type = {RequestMethod.GET})
    public String getItemsByItemId(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pathVariable;
        try {
            pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (JsonSyntaxException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        Item item = itemService.getItemById(pathVariable);
        ItemDTO itemDTO = ConversionUtils.convert(item, ItemDTO.class);
        itemDTO.setCoverUrl(ResourcePathConstant.VIRTUAL_ITEM_COVER_BASE + itemDTO.getCoverUrl());
        return ResultUtils.getResult(ApiResultCode.SUCCESS, itemDTO);

    }

    public String updateItem(HttpInfoWrapper httpInfoWrapper) {
        return "更新项目";
    }

    /**
     * 获取指定用户所创建的项目
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel
    @MyRequestPath(value = "/items/user", type = {RequestMethod.GET})
    public String getItemsByUserId(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer userId = httpInfoWrapper.getUser().getUserId();
        List<ItemDTO> items = itemService.getItemsByUserId(userId);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, items);
    }

    /**
     * 上传项目封面
     *
     * @param httpInfoWrapper
     * @return
     */
    @MyRequestPath(value = "/items/cover", type = {RequestMethod.POST})
    public String uploadItemCover(HttpInfoWrapper httpInfoWrapper) {
        Part coverPart = httpInfoWrapper.getPart("item_cover");

        if (coverPart == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        try {
            String fileName = FileUtils.saveResourceByPart(coverPart, ResourcePathConstant.DISK_ITEM_COVER_TEMP_BASE_URL);
            return ResultUtils.getResult(ApiResultCode.SUCCESS, ResourcePathConstant.VIRTUAL_ITEM_COVER_TEMP_BASE + fileName);
        } catch (Exception e) {
            logger.error("项目封面上传处理失败", e);
            return ResultUtils.getResult(ApiResultCode.RESOURCE_STORAGE_FAILED);
        }
    }

    /**
     * 加入项目
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel(LevelCode.COMMON_LOGIN_LEVEL)
    @MyRequestPath(value = "/items/enter", type = {RequestMethod.POST})
    public String joinItem(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String jsonString = httpInfoWrapper.getJsonString();
        ItemMemberMap itemMemberMap = GsonUtils.j2O(jsonString, ItemMemberMap.class);
        if (itemMemberMap == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        itemMemberMapService.addMemberMap(itemMemberMap);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 获取用户加入的项目
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/items/participated/user/?", type = {RequestMethod.GET})
    public String getItemUserParticipates(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pathVariable;
        try {
            pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (JsonSyntaxException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        List<ItemDTO> itemDTOS = itemService.getUserEnterItemList(pathVariable);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, itemDTOS);
    }

    /**
     * 根据项目id获取项目成员
     * 权限
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel(LevelCode.COMMON_LOGIN_LEVEL)
    @MyRequestPath(value = "/items/members/?", type = {RequestMethod.GET})
    public String getItemMember(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer itemId = null;
        try {
            //项目id
            itemId = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (Exception e) {
            logger.error("动态参数解析错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //用户id
        Integer userId = httpInfoWrapper.getUser().getUserId();
        //项目条件
        if (verifyItemAndUser(itemId, userId)) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHORIZED_ACCESS);
        }

        Integer pageSize = Integer.valueOf(httpInfoWrapper.getParameter("pageSize"));
        Integer pageNum = Integer.valueOf(httpInfoWrapper.getParameter("pageNum"));
        Page<ItemMemberVO> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, itemService.getItemMemberVoList(page, itemId));
    }

    /**
     * 根据项目id获取申请列表
     * 权限
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel(LevelCode.COMMON_LOGIN_LEVEL)
    @MyRequestPath(value = "/items/apply/list/?", type = {RequestMethod.GET})
    public String getItemApplyList(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pathVariable;
        try {
            pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (JsonSyntaxException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //项目id
        Integer itemId = pathVariable;

        Integer userId = httpInfoWrapper.getUser().getUserId();
        if (verifyItemAndUser(itemId, userId)) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHORIZED_ACCESS);
        }
        Integer pageSize = Integer.valueOf(httpInfoWrapper.getParameter("pageSize"));
        Integer pageNum = Integer.valueOf(httpInfoWrapper.getParameter("pageNum"));
        Page<ItemMemberVO> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, itemService.getItemReviewVOList(page, itemId));
    }

    /**
     * 项目成员审核
     * 权限
     *
     * @param httpInfoWrapper
     * @return
     */
    @AccessLevel(LevelCode.COMMON_LOGIN_LEVEL)
    @MyRequestPath(value = "/items/audit", type = {RequestMethod.PUT})
    public String agreeToApply(HttpInfoWrapper httpInfoWrapper) throws Exception {
        //用户id
        Integer userId = httpInfoWrapper.getUser().getUserId();
        //审核列表id
        Integer list_id;
        //审核结果 true代表同意，false代表拒绝
        Integer result;
        try {
            String jsonString = httpInfoWrapper.getJsonString();
            Map<String, Object> jsonToMap = GsonUtils.jsonToMap(jsonString);
            list_id = Integer.valueOf((String) jsonToMap.get("list_id"));
            result = Integer.valueOf((String) jsonToMap.get("result"));
        } catch (Exception e) {
            logger.error("请求语法错误", e);
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        ItemMemberMap memberMap = itemMemberMapService.getItemMemberById(list_id);
        if (memberMap == null) {
            return ResultUtils.getResult(ApiResultCode.REJECT_THE_REQUEST);
        }
        Integer itemId = memberMap.getItemId();

        if (verifyItemAndUser(itemId, userId)) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHORIZED_ACCESS);
        }
        memberMap.setStatus(result);
        //更新成员映射
        itemMemberMapService.checkMemberMap(memberMap);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 删除项目成员
     * 权限
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */

    @AccessLevel(LevelCode.COMMON_LOGIN_LEVEL)
    @MyRequestPath(value = "/items/members/?", type = {RequestMethod.DELETE})
    public String deleteItemMember(HttpInfoWrapper httpInfoWrapper) throws Exception {
        Integer pathVariable;
        try {
            pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        } catch (JsonSyntaxException e) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        //成员列表id
        Integer listId = pathVariable;
        //项目id
        Integer itemId = Integer.valueOf(httpInfoWrapper.getParameter("itemId"));
        //用户id
        Integer userId = httpInfoWrapper.getUser().getUserId();

        if (verifyItemAndUser(itemId, userId)) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHORIZED_ACCESS);
        }
        ItemMemberMap itemMemberMap = new ItemMemberMap();
        itemMemberMap.setId(listId);
        itemMemberMapService.removeMember(itemMemberMap);
        return ResultUtils.getResult(ApiResultCode.SUCCESS);
    }

    /**
     * 判断项目是否属于用户
     *
     * @param itemId
     * @param userId
     * @return 不属于返回true, 反之返回false
     * @throws Exception
     */
    private boolean verifyItemAndUser(Integer itemId, Integer userId) throws Exception {
        //项目条件
        Item itemCondition = new Item();
        itemCondition.setItemId(itemId);
        itemCondition.setUserId(userId);
        Item entity = itemService.getItemByItemEntity(itemCondition);
        //判断该项目是否属于此用户
        if (entity == null) {
            return true;
        }
        return false;
    }
}

package team.skylinekids.commonweal.web.controller;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
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
import team.skylinekids.commonweal.service.ItemBOService;
import team.skylinekids.commonweal.service.ItemMemberMapService;
import team.skylinekids.commonweal.service.ItemService;
import team.skylinekids.commonweal.utils.CategoryUtils;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

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
    @MyRequestPath(value = "/items", type = {RequestMethod.POST})
    public String createItem(HttpInfoWrapper httpInfoWrapper) throws Exception {
        if (!httpInfoWrapper.isLogin()) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHENTICATED);
        }
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
        ItemCondition itemCondition = GsonUtils.j2O(httpInfoWrapper.getParameter("json"), ItemCondition.class);
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
        Item item = itemService.getItemById(httpInfoWrapper.getPathVariable(Integer.class));
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
    @MyRequestPath(value = "/items/user/?", type = {RequestMethod.GET})
    public String getItemsByUserId(HttpInfoWrapper httpInfoWrapper) throws Exception {
        if (!httpInfoWrapper.isLogin()) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHENTICATED);
        }
        Integer pathVariable = httpInfoWrapper.getPathVariable(Integer.class);
        if (!httpInfoWrapper.getUser().getUserId().equals(pathVariable)) {
            return ResultUtils.getResult(ApiResultCode.UNAUTHENTICATED);
        }
        List<ItemDTO> items = itemService.getItemsByUserId(pathVariable);
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
    @MyRequestPath(value = "/items/enter", type = {RequestMethod.POST})
    public String joinItem(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String jsonString = httpInfoWrapper.getJsonString();
        ItemMemberMap itemMemberMap = GsonUtils.j2O(jsonString, ItemMemberMap.class);
        if (itemMemberMap == null) {
            return ResultUtils.getResult(ApiResultCode.REQUEST_SYNTAX_ERROR);
        }
        itemMemberMapService.addMember(itemMemberMap);
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
        List<ItemDTO> itemDTOS = itemService.getUserEnterItemList(httpInfoWrapper.getPathVariable(Integer.class));
        return ResultUtils.getResult(ApiResultCode.SUCCESS, itemDTOS);
    }

    /**
     * 根据项目id获取项目成员
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @MyRequestPath(value = "/items/members/?")
    public String getItemMember(HttpInfoWrapper httpInfoWrapper) throws Exception {
        ItemBO itemBO = itemBOService.getItemBOByItemId(httpInfoWrapper.getPathVariable(Integer.class));
        return ResultUtils.getResult(ApiResultCode.SUCCESS, itemBO);
    }

}

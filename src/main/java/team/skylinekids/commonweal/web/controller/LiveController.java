package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.enums.ResourcePathConstant;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.LiveRoom;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.LiveService;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;
import team.skylinekids.commonweal.websocket.live.LiveChatServer;
import team.skylinekids.commonweal.websocket.live.LiveManager;
import team.skylinekids.commonweal.websocket.live.RoomVO;

/**
 * 直播
 *
 * @author MysticalDream
 */
public class LiveController {

    LiveService liveService = ServiceFactory.getLiveService();

    /**
     * 创建直播间
     *
     * @param httpInfoWrapper
     */
    @AccessLevel
    @MyRequestPath(value = "/live", type = {RequestMethod.POST})
    public String createLive(HttpInfoWrapper httpInfoWrapper) throws Exception {
        User user = httpInfoWrapper.getUser();
        UserDTO userDTO = ConversionUtils.convert(user, UserDTO.class);
        String jsonString = httpInfoWrapper.getJsonString();
        LiveRoom liveRoom = GsonUtils.j2O(jsonString, LiveRoom.class);
        liveRoom.setUserId(user.getUserId());
        liveService.handleLiveRoomInfo(liveRoom);
        RoomVO roomVO = GsonUtils.j2O(jsonString, RoomVO.class);
        String uuid = liveRoom.getUuid();
        roomVO.setUserDTO(userDTO);
        roomVO.setUuid(uuid);
        LiveManager.put(uuid, roomVO);
        LiveChatServer.initLiveUsers(uuid);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, roomVO);
    }

    /**
     * 获取直播间信息
     *
     * @param httpInfoWrapper
     * @return
     * @throws Exception
     */
    @AccessLevel
    @MyRequestPath(value = "/live", type = {RequestMethod.GET})
    public String getLiveInfo(HttpInfoWrapper httpInfoWrapper) throws Exception {
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setUserId(httpInfoWrapper.getUser().getUserId());
        LiveRoom liveRoom1 = liveService.getLiveRoom(liveRoom);
        liveRoom1.setCoverUrl(ResourcePathConstant.VIRTUAL_LIVE_COVER_BASE + liveRoom1.getCoverUrl());
        return ResultUtils.getResult(ApiResultCode.SUCCESS, liveRoom1);
    }
}

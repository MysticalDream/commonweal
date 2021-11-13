package team.skylinekids.commonweal.web.controller;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.UUIDUtils;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.AccessLevel;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;
import team.skylinekids.commonweal.websocket.live.LiveChatServer;
import team.skylinekids.commonweal.websocket.live.LiveManager;
import team.skylinekids.commonweal.websocket.live.RoomVO;

import java.io.IOException;

/**
 * 直播
 *
 * @author MysticalDream
 */
public class LiveController {

    /**
     * 创建直播间
     *
     * @param httpInfoWrapper
     */
    @AccessLevel
    @MyRequestPath(value = "/live", type = {RequestMethod.POST})
    public String createLive(HttpInfoWrapper httpInfoWrapper) throws IOException {
        RoomVO roomVO = GsonUtils.j2O(httpInfoWrapper.getJsonString(), RoomVO.class);
        String uuid = UUIDUtils.getPureUUID();
        UserDTO userDTO = ConversionUtils.convert(httpInfoWrapper.getUser(), UserDTO.class);
        roomVO.setUserDTO(userDTO);
        roomVO.setCoverUrl("/upload/images/achievements/20211101124744.jpg");
        roomVO.setUuid(uuid);
        LiveManager.put(uuid, roomVO);
        LiveChatServer.initLiveUsers(uuid);
        return ResultUtils.getResult(ApiResultCode.SUCCESS, roomVO);
    }
}

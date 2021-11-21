package team.skylinekids.commonweal.service;

import team.skylinekids.commonweal.pojo.po.LiveRoom;

/**
 * @author MysticalDream
 */
public interface LiveService {
    /**
     * 获取直播间信息
     *
     * @param liveRoom
     * @return
     * @throws Exception
     */
    LiveRoom getLiveRoom(LiveRoom liveRoom) throws Exception;

    /**
     * 添加直播间信息
     *
     * @param liveRoom
     * @return
     * @throws Exception
     */
    int addLiveRoom(LiveRoom liveRoom) throws Exception;

    /**
     * 更新直播间信息
     *
     * @param liveRoom
     * @return
     * @throws Exception
     */
    int updateLiveRoom(LiveRoom liveRoom) throws Exception;

    /**
     * 处理直播间信息
     * @param liveRoom
     * @throws Exception
     */
    void handleLiveRoomInfo(LiveRoom liveRoom) throws Exception;
}

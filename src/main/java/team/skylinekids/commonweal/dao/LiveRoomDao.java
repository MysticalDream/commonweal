package team.skylinekids.commonweal.dao;

import team.skylinekids.commonweal.pojo.po.LiveRoom;

/**
 * 直播间DAO
 *
 * @author MysticalDream
 */
public interface LiveRoomDao {
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

    
}

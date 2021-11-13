package team.skylinekids.commonweal.websocket.live;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 直播管理
 *
 * @author MysticalDream
 */
public class LiveManager {

    /**
     * 直播间id的映射
     */
    private static Map<String, RoomVO> roomMap = new ConcurrentHashMap<>();

    /**
     * 设置直播间开启的用户
     *
     * @param uuid
     * @param roomVO
     */
    public static void put(String uuid, RoomVO roomVO) {
        roomMap.putIfAbsent(uuid, roomVO);
    }

    /**
     * 移除直播间
     *
     * @param uuid
     */
    public static void remove(String uuid) {
        roomMap.remove(uuid);
    }

    /**
     * 获取直播间信息
     *
     * @param uuid
     * @return
     */
    public static RoomVO get(String uuid) {
        return roomMap.get(uuid);
    }

}

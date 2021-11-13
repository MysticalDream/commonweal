package team.skylinekids.commonweal.websocket.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息缓存工具
 *
 * @author MysticalDream
 */
public final class MessageCacheUtils {
    //    private Map<Integer, List<>>
    /**
     * 直播间offer信息
     */
    private static Map<String, String> liveOfferMessage = new ConcurrentHashMap<>();

    public static void putOfferMessage(String key, String data) {
        liveOfferMessage.put(key, data);
    }

    public static String getOfferMessage(String key) {
        return liveOfferMessage.get(key);
    }
}

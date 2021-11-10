package team.skylinekids.commonweal.websocket;

import java.util.Collection;

/**
 * 消息传输
 *
 * @author MysticalDream
 */
public class TransportMessage {
    /**
     * 消息
     */
    private Message message;
    /**
     * 类型
     */
    String type;
    /**
     * 在线用户
     */
    Collection<OnlineUser> onlineUserCollections;

}

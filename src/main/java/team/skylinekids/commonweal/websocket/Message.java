package team.skylinekids.commonweal.websocket;

import java.time.LocalDateTime;

/**
 * 消息
 *
 * @author MysticalDream
 */
public class Message {
    /**
     * 来自那个用户
     */
    private Integer fromId;
    /**
     * 发送给哪个用户
     */
    private Integer toId;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private LocalDateTime time;
}

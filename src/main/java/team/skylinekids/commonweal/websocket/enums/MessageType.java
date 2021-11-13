package team.skylinekids.commonweal.websocket.enums;

/**
 * 消息类型
 *
 * @author MysticalDream
 */

public interface MessageType {
    /**
     * 信息
     */
    String MESSAGE = "message";
    /**
     * 通知
     */
    String INFO = "info";
    /**
     * 发起者信息
     */
    String OFFER_DATA = "offer_data";
    /**
     * 应答者信心
     */
    String ANSWER_DATA = "answer_data";
    /**
     * 请求发起
     */
    String REQUEST_OFFER = "request_offer";
}

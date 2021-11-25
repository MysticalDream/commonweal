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
     * 应答者信息
     */
    String ANSWER_DATA = "answer_data";
    /**
     * 请求发起
     */
    String REQUEST_OFFER = "request_offer";
    /**
     * 开始直播
     */
    String START_LIVE = "start_live";
    /**
     * 停止直播
     */
    String STOP_LIVE = "stop_live";
    /**
     * 镜像
     */
    String ROTATE = "rotate";
}

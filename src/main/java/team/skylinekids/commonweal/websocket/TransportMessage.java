package team.skylinekids.commonweal.websocket;

/**
 * 消息传输
 *
 * @author MysticalDream
 */
public class TransportMessage {
    /**
     * 消息
     */
    private Message data;
    /**
     * 类型
     */
   private String type;

    public TransportMessage(Message data, String type) {
        this.data = data;
        this.type = type;
    }

    public TransportMessage() {
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TransportMessage{" +
                "data=" + data +
                ", type='" + type + '\'' +
                '}';
    }
}

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
     * 用户名
     */
    private String fromUsername;
    /**
     * 发送给哪个用户
     */
    private Integer toId;
    /**
     * 接受的用户名
     */
    private String toUsername;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private LocalDateTime time;
    /**
     * 备注
     */
    private String remark;

    public Message(Integer fromId, Integer toId, String content, LocalDateTime time, String remark) {
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.time = time;
        this.remark = remark;
    }

    public Message(Integer fromId, Integer toId, String content) {
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.time = LocalDateTime.now();
    }

    public Message(Integer fromId) {
        this.fromId = fromId;
    }

    public Message() {
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromId=" + fromId +
                ", fromUsername='" + fromUsername + '\'' +
                ", toId=" + toId +
                ", toUsername='" + toUsername + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", remark='" + remark + '\'' +
                '}';
    }
}

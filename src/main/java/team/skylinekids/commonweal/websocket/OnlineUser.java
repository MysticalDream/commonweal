package team.skylinekids.commonweal.websocket;

import javax.websocket.Session;

/**
 * 在线用户
 *
 * @author MysticalDream
 */
public class OnlineUser {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 会话 不需要序列化
     */

    transient private Session session;

    public OnlineUser() {
    }

    public OnlineUser(Integer userId, String username, Session session) {
        this.userId = userId;
        this.username = username;
        this.session = session;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

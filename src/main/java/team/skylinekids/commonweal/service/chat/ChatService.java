package team.skylinekids.commonweal.service.chat;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.pojo.bo.websocket.OnlineUser;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.chat.config.HttpSessionConfig;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MysticalDream
 */
@ServerEndpoint(value = "/chat/{userId}", configurator = HttpSessionConfig.class)
public class ChatService {

    private static Logger logger = Logger.getLogger(ChatService.class);
    /**
     * 记录连接数目
     */
    private static int onlineCount = 0;
    /**
     * 在线用户
     */
    private static Map<Integer, OnlineUser> onlineUserMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userid") String userid, Session session, EndpointConfig config) {
        logger.info("[ChatServer] connection : userid = " + userid + " , sessionId = " + session.getId());
        addOnlineCount();
        // 获取当前用户的session
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        // 获得当前用户信息
        User user = (User) httpSession.getAttribute(SessionKeyConstant.USER_STRING);
        // 将当前用户存到在线用户列表中
        OnlineUser onlineUser = new OnlineUser(user.getUserId(), user.getUsername(), session);
        onlineUserMap.put(user.getUserId(), onlineUser);
    }

    private synchronized void addOnlineCount() {
        onlineCount++;
    }
}

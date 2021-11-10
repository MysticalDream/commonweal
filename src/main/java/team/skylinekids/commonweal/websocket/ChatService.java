package team.skylinekids.commonweal.websocket;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.websocket.config.HttpSessionConfig;
import team.skylinekids.commonweal.utils.TimeUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
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

    /**
     * 接收客户端的message，判断是否有接收人而选择进行广播还是指定发送
     *
     * @param data 客户端发来的消息
     */
    @OnMessage
    public void onMessage(@PathParam("userid") Integer userid, String data) {
        logger.info("[ChatServer] onMessage : userid = " + userid + " , data = " + data);

        Map<String, Object> messageMap = GsonUtils.jsonToMap(data);
        Map<String, String> message = (Map<String, String>) messageMap.get("message");
        String to = message.get("to");
        String from = message.get("from");
        // 将用户id转换为名称
        to = this.userIdCastNickName(to);

        OnlineUser fromUser = onlineUserMap.get(from);

        String sendMessage = MessageUtils.getContent(fromUser, to, message.get("content"), message.get("time"));
        String returnData = MessageUtils.getMessage(sendMessage, (String) messageMap.get("type"), null);

        if (to == null || to.equals("")) { // 进行广播
            MessageUtils.broadcast(returnData.toString(), onlineUserMap.values());
        } else {
            MessageUtils.singleSend(returnData.toString(), onlineUserMap.get(from));   // 发送给自己
            String[] useridList = message.get("to").split(",");
            for (String id : useridList) {
                if (!id.equals(from)) {
                    MessageUtils.singleSend(returnData.toString(), onlineUserMap.get(id)); // 分别发送给指定的用户
                }
            }
        }
    }

    /**
     * 连接关闭方法
     */
    @OnClose
    public void onClose(@PathParam("userid") Integer userid, Session session, CloseReason closeReason) {

        logger.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() +
                " , closeCode = " + closeReason.getCloseCode().getCode() + " , closeReason = " + closeReason.getReasonPhrase());

        // 减少当前用户
        subOnlineCount();

        // 移除的用户信息
        OnlineUser removeUser = onlineUserMap.remove(userid);
        onlineUserMap.remove(userid);

        // 通知所有在线用户，当前用户下线
        String content = "[" + TimeUtils.getTime24() + " : " + removeUser.getUsername() + " 离开聊天室，当前在线人数为 " + getOnlineCount() + "位" + "]";
        Map<String, Object> msg = new HashMap<>();
        msg.put("content", content);
        if (onlineUserMap.size() > 0) {
            String message = MessageUtils.getMessage(msg.toString(), MessageUtils.NOTICE, onlineUserMap.values());
            MessageUtils.broadcast(message, onlineUserMap.values());
        } else {
            logger.info("content : [" + TimeUtils.getTime24() + " : " + removeUser.getUsername() + " 离开聊天室，当前在线人数为 " + getOnlineCount() + "位" + "]");
        }

    }

    public synchronized void subOnlineCount() {
        onlineCount--;
    }

    public static int getOnlineCount() {
        return onlineCount;
    }

    private synchronized void addOnlineCount() {
        onlineCount++;
    }

    /**
     * 发生错误
     *
     * @param throwable
     */
    @OnError
    public void onError(@PathParam("userid") String userid, Session session, Throwable throwable) {
        logger.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() + " , throwable = " + throwable.getMessage());
    }

    /**
     * 将用户id转换为名称
     *
     * @param userIds
     * @return
     */
    private String userIdCastNickName(String userIds) {
        String niceNames = "";
        if (userIds != null && !userIds.equals("")) {
            String[] useridList = userIds.split(",");
            String toName = "";
            for (String id : useridList) {
                toName = toName + onlineUserMap.get(id).getUsername() + ",";
            }
            niceNames = toName.substring(0, toName.length() - 1);
        }
        return niceNames;
    }
}

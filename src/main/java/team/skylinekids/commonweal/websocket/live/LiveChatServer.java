package team.skylinekids.commonweal.websocket.live;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.websocket.Message;
import team.skylinekids.commonweal.websocket.MessageUtils;
import team.skylinekids.commonweal.websocket.OnlineUser;
import team.skylinekids.commonweal.websocket.TransportMessage;
import team.skylinekids.commonweal.websocket.config.HttpSessionConfig;
import team.skylinekids.commonweal.websocket.enums.MessageType;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 直播聊天
 *
 * @author MysticalDream
 */
@ServerEndpoint(value = "/livechat/{liveId}", configurator = HttpSessionConfig.class)
public class LiveChatServer {

    private static Logger logger = Logger.getLogger(LiveChatServer.class);

    private static Map<String, Set<OnlineUser>> liveUsers = new ConcurrentHashMap<>();

    private static Map<String, Session> liveSession = new ConcurrentHashMap<>();


    private OnlineUser onlineUser;


    @OnOpen
    public void onOpen(@PathParam("liveId") String liveId, Session session, EndpointConfig config) throws IOException {
        Integer userId = (Integer) config.getUserProperties().get(SessionKeyConstant.USER_ID);
        Set<OnlineUser> userSet = liveUsers.get(liveId);
        if (userSet == null) {
            session.close(new CloseReason(CloseReason.CloseCodes.getCloseCode(4004), "未知房间"));
            return;
        }
        RoomVO roomVO = LiveManager.get(liveId);
        Integer id = roomVO.getUserDTO().getUserId();
        //房间主人
        if (id.equals(userId)) {
            liveSession.put(roomVO.getUuid(), session);
        }

        onlineUser = new OnlineUser(userId, (String) config.getUserProperties().get(SessionKeyConstant.USER_NAME), session);

        userSet.add(onlineUser);

        Message message = new Message(userId, null, "进入直播间");
        message.setFromUsername(onlineUser.getUsername());
        TransportMessage transportMessage = new TransportMessage(message, MessageType.MESSAGE);
        MessageUtils.broadcast(GsonUtils.o2J(transportMessage), userSet);
    }


    @OnMessage
    public void onMessage(@PathParam("liveId") String liveId, String data) throws IOException {
        Session session = liveSession.get(liveId);
        TransportMessage message = GsonUtils.j2O(data, TransportMessage.class);
        System.out.println(message);
        switch (message.getType()) {
            case MessageType.REQUEST_OFFER:
            case MessageType.ANSWER_DATA:
                session.getBasicRemote().sendText(data);
                break;
            case MessageType.OFFER_DATA:
                System.out.println("开始发送offer_data");
                Integer toId = message.getData().getToId();
                Session sessionById = getSessionById(liveId, toId);
                sessionById.getBasicRemote().sendText(data);
                break;
            default:
        }
       // MessageUtils.broadcast(GsonUtils.o2J(message), liveUsers.get(liveId));
    }

    @OnClose
    public void onClose(@PathParam("liveId") String liveId, Session session, CloseReason closeReason) {
        System.out.println("关闭原因:" + closeReason.toString());
        Set<OnlineUser> userSet = liveUsers.get(liveId);
        if (userSet == null) {
            return;
        }
        userSet.remove(onlineUser);

        Message message = new Message(onlineUser.getUserId(), null, "离开直播间");
        message.setFromUsername(onlineUser.getUsername());
        TransportMessage transportMessage = new TransportMessage(message, MessageType.MESSAGE);

        MessageUtils.broadcast(GsonUtils.o2J(transportMessage), userSet);
    }

    @OnError
    public void onError(@PathParam("liveId") String liveId, Session
            session, Throwable throwable) {
    }

    public static void initLiveUsers(String uuid) {
        LiveChatServer.liveUsers.put(uuid, new HashSet<>());
    }

    private static Session getSessionById(String livId, Integer id) {
        Set<OnlineUser> onlineUsers = liveUsers.get(livId);
        for (OnlineUser user :
                onlineUsers) {
            if (user.getUserId().equals(id)) {
                return user.getSession();
            }
        }
        return null;
    }
}

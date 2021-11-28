package team.skylinekids.commonweal.websocket.live;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.dao.LiveRoomDao;
import team.skylinekids.commonweal.dao.UserDao;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.factory.DaoFactory2;
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
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static UserDao userDao = DaoFactory2.getDaoImpl(UserDao.class);
    private static LiveRoomDao liveRoomDao = DaoFactory2.getDaoImpl(LiveRoomDao.class);
    /**
     * 线程池
     */
    private static final ExecutorService es = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), nameThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private OnlineUser onlineUser;

    static {
//        try {
//            List<LiveRoom> liveRooms = liveRoomDao.getLiveRooms();
//            for (LiveRoom liveRoom :
//                    liveRooms) {
//                User user = userDao.getUserById(liveRoom.getUserId());
//                UserDTO userDTO = ConversionUtils.convert(user, UserDTO.class);
//                RoomVO roomVO = ConversionUtils.convert(liveRoom, RoomVO.class);
//                roomVO.setUserDTO(userDTO);
//                String uuid = liveRoom.getUuid();
//                LiveManager.put(uuid, roomVO);
//                LiveChatServer.initLiveUsers(uuid);
//            }
//        } catch (Exception e) {
//            logger.error("数据库操作失败", e);
//        }
    }

    /**
     * 自定义threadfactory
     *
     * @return
     */
    private static ThreadFactory nameThreadFactory() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ThreadFactory threadFactory = (Runnable runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setName("线程号:" + atomicInteger.getAndIncrement());
            return thread;
        };
        return threadFactory;
    }

    /**
     * 开启连接
     *
     * @param liveId
     * @param session
     * @param config
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("liveId") String liveId, Session session, EndpointConfig config) throws IOException {
        Integer userId = (Integer) config.getUserProperties().get(SessionKeyConstant.USER_ID);
        Set<OnlineUser> userSet = liveUsers.get(liveId);
        if (userSet == null) {
            session.close(new CloseReason(CloseReason.CloseCodes.getCloseCode(4004), "未知房间"));
            return;
        }
        if (userId == null) {
            session.close();
            return;
        }
        RoomVO roomVO = LiveManager.get(liveId);
        Integer id = roomVO.getUserDTO().getUserId();

        Message message = new Message(userId, null, "进入直播间");
        //房间主人
        if (id.equals(userId)) {
            System.out.println("主播id:" + id + ",open:" + session.isOpen());
            liveSession.put(roomVO.getUuid(), session);
            message.setRemark("live_own");
        }

        onlineUser = new OnlineUser(userId, (String) config.getUserProperties().get(SessionKeyConstant.USER_NAME), session);

        userSet.add(onlineUser);


        message.setFromUsername(onlineUser.getUsername());

        TransportMessage transportMessage = new TransportMessage(message, MessageType.MESSAGE);
//        MessageUtils.broadcast(GsonUtils.o2J(transportMessage), userSet);
    }


    @OnMessage
    public void onMessage(@PathParam("liveId") String liveId, String data) throws IOException {
        //主播session
        Session session = liveSession.get(liveId);
        boolean flag = false;
        Integer userId = this.onlineUser.getUserId();
        RoomVO roomVO = LiveManager.get(liveId);
        Integer id = roomVO.getUserDTO().getUserId();
        if (userId.equals(id)) {
            flag = true;
        }
        TransportMessage transportMessage = GsonUtils.j2O(data, TransportMessage.class);
        System.out.println(transportMessage);
        //TODO 此处赶时间，不过可以用工厂加策略模式替换解耦
        switch (transportMessage.getType()) {
            case MessageType.REQUEST_OFFER:
            case MessageType.ANSWER_DATA:
                session.getBasicRemote().sendText(data);
                break;
            case MessageType.OFFER_DATA:
                sendOfferHandler(liveId, data, transportMessage);
                break;
            case MessageType.START_LIVE:
                startLiveHandler(liveId, session);
                break;
            case MessageType.STOP_LIVE:
                stopLiveHandler(liveId, data);
                break;
            case MessageType.MESSAGE:
                Message data1 = transportMessage.getData();
                data1.setFromUsername(onlineUser.getUsername());
                if (flag) {
                    data1.setRemark("live_own");
                }
                MessageUtils.broadcast(GsonUtils.o2J(transportMessage), liveUsers.get(liveId));
                break;
            case MessageType.ROTATE:
                MessageUtils.broadcast(data, liveUsers.get(liveId));
                break;
            default:
                logger.info("没有该消息处理:" + transportMessage.getType());
        }
        // MessageUtils.broadcast(GsonUtils.o2J(transportMessage), liveUsers.get(liveId));
    }

    /**
     * 停止直播
     *
     * @param liveId
     * @param data
     */
    private static void stopLiveHandler(String liveId, String data) {
        es.execute(() -> {
            MessageUtils.broadcast(data, liveUsers.get(liveId));
        });
    }

    /**
     * 发送offer处理
     *
     * @param liveId
     * @param data
     * @param message
     * @throws IOException
     */
    private static void sendOfferHandler(String liveId, String data, TransportMessage message) throws IOException {
        System.out.println("开始发送offer_data");
        Integer toId = message.getData().getToId();
        if (toId != null) {
            Session sessionById = getSessionById(liveId, toId);
            sessionById.getBasicRemote().sendText(data);
        }
    }

    /**
     * 开启直播
     *
     * @param liveId
     * @param session
     */
    private static void startLiveHandler(String liveId, Session session) {
        //开启线程广播
        es.execute(() -> {
            for (OnlineUser onlineUser :
                    liveUsers.get(liveId)) {
                TransportMessage transportMessage = new TransportMessage(new Message(onlineUser.getUserId()), MessageType.REQUEST_OFFER);
                System.out.println("开启直播通知:" + transportMessage);
                try {
                    synchronized (session) {
                        session.getBasicRemote().sendText(GsonUtils.o2J(transportMessage));
                    }
                } catch (IOException e) {
                    logger.error("广播主播开启错误");
                }
            }
        });
    }

    @OnClose
    public void onClose(@PathParam("liveId") String liveId, Session session, CloseReason closeReason) {
//        System.out.println("关闭原因:" + closeReason.toString());
        Set<OnlineUser> userSet = liveUsers.get(liveId);
        if (userSet == null) {
            return;
        }
        userSet.remove(onlineUser);
        if (onlineUser == null) {
            return;
        }
        Message message = new Message(onlineUser.getUserId(), null, "离开直播间");
        message.setFromUsername(onlineUser.getUsername());
        TransportMessage transportMessage = new TransportMessage(message, MessageType.INFO);

//        MessageUtils.broadcast(GsonUtils.o2J(transportMessage), userSet);
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

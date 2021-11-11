package team.skylinekids.commonweal.websocket;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author ccq
 * @Description 消息类
 * @date 2017/12/16 19:08
 */
public class MessageUtils {

    private static Logger logger = Logger.getLogger(MessageUtils.class);

    /**
     * 消息类型
     */
    public static String NOTICE = "notice";     //通知
    public static String MESSAGE = "message";   //消息

    /**
     * 组装信息返回给前台
     *
     * @param message  交互信息
     * @param type     信息类型
     * @param userList 在线列表
     * @return "massage" : {
     * "from" : "xxx",
     * "to" : "xxx",
     * "content" : "xxx",
     * "time" : "xxxx.xx.xx"
     * },
     * "type" : {notice|message},
     * "list" : {[xx],[xx],[xx]}
     */
    public static String getMessage(String message, String type, Collection<OnlineUser> userList) {

        Map<String, Object> msg = new HashMap<>();

        msg.put("message", message);
        msg.put("type", type);

        if (userList.size() != 0) {
            msg.put("list", userList);
        }
        return GsonUtils.o2J(msg);
    }

    /**
     * 消息内容
     *
     * @param fromUser
     * @param to
     * @param content
     * @param time
     * @return {
     * "from" : "xxx",
     * "to" : "xxx",
     * "content" : "xxx",
     * "time" : "xxxx.xx.xx"
     * }
     */
    public static String getContent(OnlineUser fromUser, String to, String content, String time) {
        Map<String, Object> contentMap = new HashMap();
        contentMap.put("from", fromUser);
        contentMap.put("to", to);
        contentMap.put("content", content);
        contentMap.put("time", time);
        return GsonUtils.o2J(contentMap);
    }

    /**
     * 广播除了
     *
     * @param message
     * @param onlineUser
     * @param onlineUsers
     */
    public static void broadcastWithout(String message, OnlineUser onlineUser, Collection<OnlineUser> onlineUsers) {
        /***************************在线用户***************************/
        StringBuffer userStr = new StringBuffer();
        for (OnlineUser user : onlineUsers) {
            userStr.append(user.getUsername() + ",");
        }
        userStr.deleteCharAt(userStr.length() - 1);
        logger.info("[broadcast] message = " + message + ", onlineUsers = " + userStr);
        /***************************在线用户***************************/
        for (OnlineUser user : onlineUsers) {
            if (user == onlineUser) {
                continue;
            }
            try {
                user.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("消息发送失败！" + e.getMessage());
                continue;
            }
        }
    }

    /**
     * 广播消息
     *
     * @param message     消息
     * @param onlineUsers 在线用户
     */
    public static void broadcast(String message, Collection<OnlineUser> onlineUsers) {
        /***************************在线用户***************************/
        StringBuffer userStr = new StringBuffer();
        for (OnlineUser user : onlineUsers) {
            userStr.append(user.getUsername() + ",");
        }
        userStr.deleteCharAt(userStr.length() - 1);
        logger.info("[broadcast] message = " + message + ", onlineUsers = " + userStr);
        /***************************在线用户***************************/
        for (OnlineUser user : onlineUsers) {
            try {
                user.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("消息发送失败！" + e.getMessage());
                continue;
            }
        }
    }

    /**
     * 对特定用户发送消息
     *
     * @param message
     * @param onlineUser
     */
    public static void singleSend(String message, OnlineUser onlineUser) {
        // logger.info("[singleSend] message = " + message + ", toUser = " + onlineUser.getUsername());
        try {
            onlineUser.getSession().getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("消息发送失败！" + e.getMessage());
        }
    }
}

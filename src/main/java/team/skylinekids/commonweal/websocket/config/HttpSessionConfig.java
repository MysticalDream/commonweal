package team.skylinekids.commonweal.websocket.config;

import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.pojo.po.User;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author MysticalDream
 */
public class HttpSessionConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        User user = (User) session.getAttribute(SessionKeyConstant.USER_STRING);
        if (user == null) {
            return;
        }
        sec.getUserProperties().put(HttpSession.class.getName(), session);
        sec.getUserProperties().put(SessionKeyConstant.USER_ID, user.getUserId());
        sec.getUserProperties().put(SessionKeyConstant.USER_NAME, user.getUsername());
    }
}

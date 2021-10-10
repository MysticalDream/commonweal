package team.skylinekids.commonweal.pojo.bo;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.MediaType;
import team.skylinekids.commonweal.pojo.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装http常用信息
 *
 * @author MysticalDream
 */
public class HttpInfoWrapper {

    private final Logger logger = Logger.getLogger(HttpInfoWrapper.class);
    /**
     * http响应
     */
    private HttpServletResponse httpServletResponse;
    /**
     * http请求
     */
    private HttpServletRequest httpServletRequest;
    /**
     * http会话
     */
    private HttpSession httpSession;
    /**
     * 登录的用户
     */
    private User user;
    /**
     * 保存part的映射
     */
    private Map<String, Part> partMap = new ConcurrentHashMap<>();
    /**
     * 参数map
     */
    private Map<String, String[]> parameterMap;
    /**
     * 会话中保存的用户信息的键值
     */
    private final String USER_STRING = "USER";


    public HttpInfoWrapper(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        this.httpServletResponse = httpServletResponse;
        this.httpServletRequest = httpServletRequest;
        init();
    }

    /**
     * 初始化一些常用变量
     */
    private void init() {
        //Part映射初始化
        try {
            this.httpSession = httpServletRequest.getSession();
            this.user = (User) httpSession.getAttribute(USER_STRING);
            //获取数据类型
            String header = httpServletRequest.getHeader("Content-Type");

            if (MediaType.MULTIPART_FORM_DATA.equals(header) || MediaType.MULTIPART_MIXED.equals(header)) {
                Collection<Part> parts = httpServletRequest.getParts();
                for (Part part :
                        parts) {
                    if (partMap.put(part.getName(), part) != null) {
                        throw new RuntimeException("Part的name重复了");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取Part部分出现异常", e);
        }
        //请求参数map
        parameterMap = httpServletRequest.getParameterMap();
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public User getUser() {
        return user;
    }

    public void setUserToSession(User user) {
        httpSession.setAttribute(USER_STRING, user);
    }

    public Part getPart(String name) {
        return partMap.get(name);
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    /**
     * 获取参数，当参数有多个值时只取第一个
     *
     * @param name
     * @return
     */
    public String getParameter(String name) {
        return parameterMap.get(name)[0];
    }
}

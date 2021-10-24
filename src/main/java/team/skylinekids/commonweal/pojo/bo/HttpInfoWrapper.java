package team.skylinekids.commonweal.pojo.bo;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.MediaType;
import team.skylinekids.commonweal.enums.SessionKeyConstant;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
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
     * 保存part的映射
     */
    private Map<String, Part> partMap = new ConcurrentHashMap<>();
    /**
     * 保存Cookie的map,键为cookie的键
     */
    private Map<String, Cookie> cookieMap = new ConcurrentHashMap<>();
    /**
     * 参数map
     */
    private Map<String, String[]> parameterMap;
    /**
     * 路径参数
     */
    private String pathVariable;

    public HttpInfoWrapper(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, String pathVariable) {
        this.httpServletResponse = httpServletResponse;
        this.httpServletRequest = httpServletRequest;
        this.pathVariable = pathVariable;
        init();
    }

    /**
     * 初始化一些常用变量
     */
    private void init() {
        //Part映射初始化
        try {
            this.httpSession = httpServletRequest.getSession();
            //获取数据类型
            String header = httpServletRequest.getHeader("Content-Type");
            if (header != null) {
                if (header.contains(MediaType.MULTIPART_FORM_DATA) || header.contains(MediaType.MULTIPART_MIXED)) {
                    Collection<Part> parts = httpServletRequest.getParts();
                    for (Part part :
                            parts) {
                        if (partMap.put(part.getName(), part) != null) {
                            throw new RuntimeException("Part的name重复了");
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("获取Part部分出现异常", e);
        }
        //请求参数map
        parameterMap = new HashMap<>(httpServletRequest.getParameterMap());
        //获取Cookies
        initCookie();
    }

    private void initCookie() {
        //86400s一天
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return;
        }
        try {
            for (Cookie cookie :
                    cookies) {
                cookie.setValue(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                cookieMap.putIfAbsent(cookie.getName(), cookie);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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


    public User getUser() {
        return (User) httpSession.getAttribute(SessionKeyConstant.USER_STRING);
    }

    public void setHttpSessionAttribute(String name, Object value) {
        httpSession.setAttribute(name, value);
    }

    public Object getHttpSessionAttribute(String name) {
        return httpSession.getAttribute(name);
    }

    public void removeHttpSessionAttribute(String name) {
        httpSession.removeAttribute(name);
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public boolean isUserLogin() {
        return this.getUser() != null;
    }

    public void setUserToSession(User user) {
        httpSession.setAttribute(SessionKeyConstant.USER_STRING, user);
    }

    public void removeUserFromSession() {
        httpSession.invalidate();
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
        String[] strings = parameterMap.get(name);
        if (strings != null) {
            return strings[0];
        }
        return null;
    }

    /**
     * 根据提供的类型获取参数，当参数有多个值时只取第一个
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getParameter(String name, Class<? extends Number> clazz) throws ClassCastException {
        String[] strings = parameterMap.get(name);
        if (strings != null) {
            return (T) GsonUtils.j2O(strings[0], clazz);
        }
        return null;
    }

    /**
     * 设置Cookie
     *
     * @param key
     * @param value
     * @param expiry 秒
     */
    public void setCookie(String key, String value, int expiry) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expiry);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 设置Cookie
     *
     * @param key
     * @param value
     */
    public void setCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 设置Cookie
     *
     * @param key
     * @param value
     * @param url   带回路径
     */
    public void setCookie(String key, String value, String url) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(url);
        httpServletResponse.addCookie(cookie);
    }


    /**
     * 获取Cookie对象
     *
     * @param key
     * @return
     */
    public Cookie getCookie(String key) {
        return cookieMap.get(key);
    }

    /**
     * 用map批量添加cookie
     *
     * @param map
     * @param expiry 秒
     */
    public void setCookies(Map<String, String> map, int expiry) {
        try {
            for (Map.Entry<String, String> entry :
                    map.entrySet()) {
                Cookie cookie = new Cookie(entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
                cookie.setMaxAge(expiry);
                httpServletResponse.addCookie(cookie);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取路径参数值
     *
     * @param clazz   路径参数的类类型
     * @param <T>参数类型
     * @return
     */
    public <T> T getPathVariable(Class<T> clazz) {
        return pathVariable == null ? null : GsonUtils.j2O(pathVariable, clazz);
    }

    /**
     * 获取json数据字符串
     *
     * @return
     * @throws IOException
     */
    public String getJsonString() throws IOException {
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    /**
     * 验证用户是否登录且登录用户id和Cookie的id是否一致
     *
     * @return
     */
    public boolean isLogin() {
        User user = getUser();
        if (user != null) {
            try {
                if (user.getUserId().equals(Integer.parseInt(getCookie("userId").getValue()))) {
                    return true;
                }
            } catch (NumberFormatException e) {
                logger.error("用户id转化错误", e);
            } catch (Exception e) {
                logger.error("登录Cookie验证出错", e);
            }
        }
        return false;
    }
}

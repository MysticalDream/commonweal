package team.skylinekids.commonweal.web.core;

import team.skylinekids.commonweal.enums.RequestMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 映射处理信息
 *
 * @author MysticalDream
 */
public class HandleInfo {
    /**
     * 实例对象
     */
    private Object object;
    /**
     * 请求类型和方法映射
     */
    private Map<String, Method> requestTypeMethodMap = new ConcurrentHashMap<>();

    public HandleInfo(Object object, Method method, RequestMethod[] requestMethods) {
        this.object = object;
        handleMethodInfo(requestMethods, method);
    }

    /**
     * 处理请求类型和方法映射
     *
     * @param requestMethods
     * @param method
     */
    private void handleMethodInfo(RequestMethod[] requestMethods, Method method) {
        for (RequestMethod requestMethod :
                requestMethods) {
            requestTypeMethodMap.put(requestMethod.getMethod(), method);
        }
    }

    public HandleInfo() {

    }

    public Object getObject() {
        return object;
    }

    public Map<String, Method> getRequestTypeMethodMap() {
        return requestTypeMethodMap;
    }

    /**
     * 通过请求类型获取方法
     *
     * @param method
     * @return
     */
    public Method getMethodByRequestType(String method) {
        return requestTypeMethodMap.get(method);
    }

    /**
     * 设置将请求方法和方法对应
     *
     * @param requestMethods
     * @param method
     * @return
     */
    public boolean insertRequestTypeMethodMap(RequestMethod[] requestMethods, Method method) {
        for (RequestMethod requestMethod :
                requestMethods) {
            if (requestTypeMethodMap.putIfAbsent(requestMethod.getMethod(), method) != null) {
                return false;
            }
        }
        return true;
    }

}

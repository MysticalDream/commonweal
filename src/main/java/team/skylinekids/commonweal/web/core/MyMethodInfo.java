package team.skylinekids.commonweal.web.core;

import team.skylinekids.commonweal.enums.RequestType;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 映射对象，每个对象带有一个方法，用于处理请求
 *
 * @author MysticalDream
 */
public class MyMethodInfo {
    /**
     * 实例对象
     */
    private Object object;
    /**
     * 请求类型和方法映射
     */
    private Map<String, Method> requestTypeMethodMap = new ConcurrentHashMap<>();

    public MyMethodInfo(Object object, Method method, RequestType[] requestTypes) {
        this.object = object;
        handleMethodInfo(requestTypes, method);
    }

    /**
     * 处理请求类型和方法映射
     *
     * @param requestTypes
     * @param method
     */
    private void handleMethodInfo(RequestType[] requestTypes, Method method) {
        for (RequestType requestType :
                requestTypes) {
            requestTypeMethodMap.put(requestType.getMethod(), method);
        }
    }

    public MyMethodInfo() {

    }

    public Object getObject() {
        return object;
    }

    public Map<String, Method> getRequestTypeMethodMap() {
        return requestTypeMethodMap;
    }

    /**
     * 通过请求类型获取方法
     * @param method
     * @return
     */
    public Method getMethodByRequestType(String method) {
        return requestTypeMethodMap.get(method);
    }

    /**
     * 设置将请求方法和方法对应
     *
     * @param requestTypes
     * @param method
     * @return
     */
    public boolean insertRequestTypeMethodMap(RequestType[] requestTypes, Method method) {
        for (RequestType requestType :
                requestTypes) {
            if (requestTypeMethodMap.putIfAbsent(requestType.getMethod(), method) != null) {
                return false;
            }
        }
        return true;
    }

}

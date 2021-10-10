package team.skylinekids.commonweal.web.core;

import team.skylinekids.commonweal.enums.RequestType;

import java.lang.reflect.Method;

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
     * 对应方法
     */
    private Method method;
    /**
     * 请求类型
     */
    private RequestType[] requestTypes;

    public MyMethodInfo(Object object, Method method, RequestType[] requestTypes) {
        this.object = object;
        this.method = method;
        this.requestTypes = requestTypes;
    }

    public MyMethodInfo() {
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public RequestType[] getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(RequestType[] requestTypes) {
        this.requestTypes = requestTypes;
    }

}

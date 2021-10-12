package team.skylinekids.commonweal.web.core;

import java.lang.reflect.Method;

/**
 * 方法信息
 *
 * @author MysticalDream
 */
public class MethodInfo {
    /**
     * 对应方法
     */
    private Method method;
    /**
     * 是否需要登录
     */
    private boolean AUTHORIZATION_IS_REQUIRED;

    public MethodInfo(Method method, boolean AUTHORIZATION_IS_REQUIRED) {
        this.method = method;
        this.AUTHORIZATION_IS_REQUIRED = AUTHORIZATION_IS_REQUIRED;
    }

    public MethodInfo(Method method) {
        this.method = method;
        this.AUTHORIZATION_IS_REQUIRED = false;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public boolean isAUTHORIZATION_IS_REQUIRED() {
        return AUTHORIZATION_IS_REQUIRED;
    }

    public void setAUTHORIZATION_IS_REQUIRED(boolean AUTHORIZATION_IS_REQUIRED) {
        this.AUTHORIZATION_IS_REQUIRED = AUTHORIZATION_IS_REQUIRED;
    }
}

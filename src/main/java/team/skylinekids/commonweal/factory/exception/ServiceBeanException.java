package team.skylinekids.commonweal.factory.exception;

/**
 * 服务bean异常
 *
 * @author MysticalDream
 */
public class ServiceBeanException extends RuntimeException {
    public ServiceBeanException() {
        super("Service bean构造异常");
    }

    public ServiceBeanException(String message) {
        super("Service bean构造异常:" + message);
    }
}

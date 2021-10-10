package team.skylinekids.commonweal.web.core.exception;

/**
 * mvc异常
 *
 * @author MysticalDream
 */
public class HandlerException extends Exception {

    public HandlerException() {
        super("映射处理异常");
    }

    public HandlerException(String message) {
        super("映射处理异常:" + message);
    }
}

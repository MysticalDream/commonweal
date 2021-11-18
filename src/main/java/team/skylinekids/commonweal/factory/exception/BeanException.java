package team.skylinekids.commonweal.factory.exception;

/**
 * bean异常
 *
 * @author MysticalDream
 */
public class BeanException extends RuntimeException {
    public BeanException() {
        super("构造Bean异常");
    }

    public BeanException(String message) {
        super("构造Bean异常:" + message);
    }
}

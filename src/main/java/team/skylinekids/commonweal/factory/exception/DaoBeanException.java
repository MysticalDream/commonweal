package team.skylinekids.commonweal.factory.exception;

/**
 * @author MysticalDream
 */
public class DaoBeanException extends RuntimeException {
    public DaoBeanException() {
        super("dao构造异常");
    }

    public DaoBeanException(String message, Exception e) {
        super("dao构造异常:" + message, e);
    }

    public DaoBeanException(String message) {
        super("dao构造异常:" + message);
    }
}

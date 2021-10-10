package team.skylinekids.commonweal.utils;

import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;


/**
 * IO工具类
 *
 * @author MysticalDream
 */
public class IOUtils {

    public static final Logger logger = Logger.getLogger(IOUtils.class);

    private IOUtils() {

    }

    /**
     * 安静的关闭流
     *
     * @param closeable
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }



}

package team.skylinekids.commonweal.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author MysticalDream
 */
public final class UUIDUtils {
    private UUIDUtils() {

    }

    /**
     * 获取UUID不带有-
     * 将生成UUID的代码加一个锁,避免重复
     *
     * @return
     */
    synchronized public static String getPureUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

package team.skylinekids.commonweal.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64工具类
 *
 * @author MysticalDream
 */
public final class Base64Utils {
    private Base64Utils() {

    }

    /**
     * 获取Base64字节数组
     *
     * @param bytes
     * @return
     */
    public static byte[] getBase64Byte(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        return encode;
    }

    /**
     * 获取base64字符串
     *
     * @param src
     * @return
     */
    public static String getBase64String(String src) {
        byte[] bytes = src.getBytes(StandardCharsets.UTF_8);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

}

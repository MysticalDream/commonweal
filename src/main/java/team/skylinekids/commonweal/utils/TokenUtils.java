package team.skylinekids.commonweal.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * token工具类
 *
 * @author MysticalDream
 */
public final class TokenUtils {
    private TokenUtils() {

    }

    /**
     * 获取令牌
     *
     * @return
     */
    public static String getToken() {
        String token = UUID.randomUUID().toString().replace("-", "");
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");

            byte[] digest = md5.digest(token.getBytes(StandardCharsets.UTF_8));

            String base64String = Base64Utils.getBase64String(digest);
            return base64String;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

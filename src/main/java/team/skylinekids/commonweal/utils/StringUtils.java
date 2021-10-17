package team.skylinekids.commonweal.utils;

/**
 * 字符串工具类
 *
 * @author MysticalDream
 */
public final class StringUtils {
    private StringUtils() {

    }

    /**
     * 判断某字符串是否为空，为空的标准是str == null 或 str.length() == 0
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    /**
     * 判断某字符串是否为空或长度为0或由空白符(whitespace)构成
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * 判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成，
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !(null == str || "".equals(str.trim()));
    }
}

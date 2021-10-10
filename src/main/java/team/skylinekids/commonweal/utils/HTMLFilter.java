package team.skylinekids.commonweal.utils;

/**
 * HTML 过滤器工具
 *
 * @author MysticalDream
 */
public final class HTMLFilter {
    /**
     * 私有化构造器
     */
    private HTMLFilter() {

    }

    /**
     * 过滤指定的消息字符串以查找HTML中的敏感字符
     *
     * @param message 过滤后的消息字符串
     */
    public static String filter(String message) {

        if (message == null) {
            return null;
        }
        char[] content = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());

    }
}

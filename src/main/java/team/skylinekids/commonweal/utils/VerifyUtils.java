package team.skylinekids.commonweal.utils;


import team.skylinekids.commonweal.enums.RegularConstant;

/**
 * 验证工具
 *
 * @author MysticalDream
 */
public class VerifyUtils {

    private VerifyUtils() {

    }

    /**
     * 用户名合法验证
     *
     * @param username
     * @return 验证用户名的合法性，合法返回true,反之返回false
     */
    public static boolean verifyUsername(String username) {
        return stringMatches(username, RegularConstant.USERNAME_REGX);
    }

    /**
     * 密码合法验证
     *
     * @param password
     * @return 验证密码的合法性，合法返回true,反之返回false
     */
    public static boolean verifyPassword(String password) {
        return stringMatches(password, RegularConstant.PASSWORD_REGX);
    }

    /**
     * 匹配方法
     *
     * @param obj             待匹配字符串对象
     * @param regularConstant 正则常量枚举
     * @return 返回匹配结果, 如果字符串对象和所给正则表达式匹配返回true，反之返回false
     */
    private static boolean stringMatches(String obj, RegularConstant regularConstant) {
        return obj != null && obj.matches(regularConstant.getRegx());
    }
}

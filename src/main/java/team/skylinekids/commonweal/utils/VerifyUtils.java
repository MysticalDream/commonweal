package team.skylinekids.commonweal.utils;


import team.skylinekids.commonweal.enums.RegularConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     * 验证身份证号
     *
     * @param idNumber
     * @return
     */
    public static boolean verifyIDNumber(String idNumber) {
        if (checkCode(idNumber)) {
            String date = idNumber.substring(6, 14);
            if (checkDate(date)) {
                if (checkProv(idNumber.substring(0, 2))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 身份证校验码验证
     *
     * @param val
     * @return
     */
    private static boolean checkCode(String val) {
        int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        String[] parity = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        String code = val.substring(17);
        if (stringMatches(val, RegularConstant.ID_CODE_REGX)) {
            int sum = 0;
            for (var i = 0; i < 17; i++) {
                sum += (val.charAt(i) - 48) * factor[i];
            }
            if (parity[sum % 11].equals(code.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 日期有效性验证
     *
     * @param val
     * @return
     */
    private static boolean checkDate(String val) {
        if (stringMatches(val, RegularConstant.ID_DATE_REGX)) {
            String year = val.substring(0, 4);
            String month = val.substring(4, 6);
            String date = val.substring(6, 8);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                dateFormat.parse(year + "-" + month + "-" + date);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 省份检查
     *
     * @param val
     * @return
     */
    private static boolean checkProv(String val) {
        Set<String> set = Set.of("11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82");
        if (stringMatches(val, RegularConstant.ID_PROV_REGX)) {
            if (set.contains(val)) {
                return true;
            }
        }
        return false;
    }

}

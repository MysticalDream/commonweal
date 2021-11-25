package team.skylinekids.commonweal.enums;

/**
 * @author MysticalDream
 */

public enum RegularConstant {
    /**
     * 用户名匹配
     * 用户名只能由由数字、26个英文字母或者下划线组成长度在3到20
     */
    USERNAME_REGX("^\\w{3,20}$"),
    /**
     * 密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间
     * 密码匹配
     */
    PASSWORD_REGX("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$"),

    ID_CODE_REGX("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$"),
    ID_DATE_REGX("^(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)$"),
    ID_PROV_REGX("^[1-9][0-9]");

    private String regx;


    RegularConstant(String regx) {
        this.regx = regx;
    }

    public String getRegx() {
        return regx;
    }
}

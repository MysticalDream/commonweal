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
    PASSWORD_REGX("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$");

    private String regx;

    RegularConstant(String regx) {
        this.regx = regx;
    }

    public String getRegx() {
        return regx;
    }
}

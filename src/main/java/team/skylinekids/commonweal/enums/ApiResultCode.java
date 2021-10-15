package team.skylinekids.commonweal.enums;

/**
 * API响应信息枚举
 * .replaceAll(" ","_").toUpperCase()
 *
 * @author MysticalDream
 */

public enum ApiResultCode {
    /**
     * 成功状态码 200
     */
    SUCCESS(200, "操作成功", true),
    /**
     * 请求重定向 302
     */
    REDIRECT(302, "重定向操作", true),
    /**
     * 客户端错误 4000-4999
     */
    REQUEST_SYNTAX_ERROR(4000, "请求语法错误", false),
    //未登录时返回
    UNAUTHENTICATED(4001, "未验证身份", false),
    REJECT_THE_REQUEST(4003, "拒绝该请求", false),
    THE_PAGE_NOT_FOUND(4004, "资源不存在", false),
    REQUEST_METHOD_NOT_ALLOWED(4005, "请求方法不允许", false),
    THE_TOKEN_IS_INVALID(4098, "令牌失效", false),
    THE_TOKEN_ERROR(4099, "客户端令牌错误", false),

    /**
     * 服务器错误5000-5999
     */
    SERVER_RUNNING_EXCEPTION(5000, "服务器运行异常", false),
    RESOURCE_STORAGE_FAILED(5500, "资源存储异常", false),
    /**
     * 用户错误6000-6999
     */
    PASSWORD_OR_USER_NAME_IS_INCORRECT(6000, "密码或用户名错误", false),

    USER_NAME_ALREADY_EXISTS(60001, "该用户名不可用", false),

    USER_PASSWORD_NOT_EQUAL(6002, "两次输入的密码不相等", false),

    INCORRECT_USERNAME_FORMAT(6003, "用户名只能由由数字、26个英文字母或者下划线组成，长度在3-20之间", false),

    INCORRECT_PASSWORD_FORMAT(6004, "密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间", false),
    UNKNOWN_USER(6005, "未知用户", false);


    int code;
    String message;
    /**
     * 请求是否有积极回应
     */
    boolean success;

    ApiResultCode(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}

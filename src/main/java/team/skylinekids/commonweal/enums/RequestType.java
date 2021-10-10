package team.skylinekids.commonweal.enums;

/**
 * HTTP请求类型
 *
 * @author MysticalDream
 */

public enum RequestType {
    /**
     * GET请求
     */
    GET("GET"),
    /**
     * POST请求
     */
    POST("POST"),
    /**
     * PUT请求
     */
    PUT("PUT"),
    /**
     * DELETE请求
     */
    DELETE("DELETE");

    private String method;

    RequestType(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}

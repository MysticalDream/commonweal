package team.skylinekids.commonweal.enums;

/**
 * HTTP请求方法
 *
 * @author MysticalDream
 */

public enum RequestMethod {
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

    RequestMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}

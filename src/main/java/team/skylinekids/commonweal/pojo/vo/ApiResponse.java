package team.skylinekids.commonweal.pojo.vo;


import team.skylinekids.commonweal.enums.ApiResultCode;

/**
 * @author MysticalDream
 */
public class ApiResponse<T> {
    /**
     * 结果码
     */
    private int code;
    /**
     * 响应消息说明
     */
    private String message;
    /**
     * 响应请求是否成功
     */
    private boolean success;
    /**
     * 响应的数据，不一定有
     */
    private T data;



    public ApiResponse(ApiResultCode apiResultCode) {
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getMessage();
        this.success = apiResultCode.isSuccess();
    }

    public ApiResponse(ApiResultCode apiResultCode, T data) {
        this(apiResultCode);
        this.data = data;
    }

    /**
     * 默认成功响应
     *
     * @param data 要响应的数据
     */
    public ApiResponse(T data) {
        this(ApiResultCode.SUCCESS, data);
    }
    

}

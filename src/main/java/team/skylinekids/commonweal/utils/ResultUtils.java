package team.skylinekids.commonweal.utils;

import com.google.gson.*;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.pojo.vo.ApiResponse;
import team.skylinekids.commonweal.utils.gson.GsonUtils;


/**
 * 结果工具类
 *
 * @author MysticalDream
 */
public class ResultUtils {
    /**
     * 私有化构造器
     */
    private ResultUtils() {

    }

    private static Gson gson = GsonUtils.getGsonInstance();

    /**
     * 返回结果的字符串形式
     *
     * @param resultCode 结果状态码
     * @param data       返回的数据
     * @param <T>        数据类型
     * @return 以字符串的形式返回结果
     */
    public static <T> String getResult(ApiResultCode resultCode, T data) {
        return gson.toJson(new ApiResponse<>(resultCode, data));
    }

    /**
     * 返回结果的字符串形式
     *
     * @param resultCode 结果状态码
     * @return 以字符串的形式返回结果
     */
    public static String getResult(ApiResultCode resultCode) {
        return gson.toJson(new ApiResponse<>(resultCode));
    }

}

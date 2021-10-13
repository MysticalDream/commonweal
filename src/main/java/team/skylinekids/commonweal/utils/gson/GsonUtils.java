package team.skylinekids.commonweal.utils.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author MysticalDream
 */
public class GsonUtils {

    private static Gson gson;

    /**
     * 序列化
     */
    private final static JsonSerializer<LocalDateTime> localDateTimeJsonSerializer = (localDateTime, type, jsonSerializationContext)
            -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    private final static JsonSerializer<LocalDate> localDateJsonSerializer = (localDate, type, jsonSerializationContext)
            -> new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    /**
     * 反序列化
     */
    private final static JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer = (json, type, jsonDeserializationContext)
            -> {
        String datetime = json.getAsJsonPrimitive().getAsString();
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    };

    private final static JsonDeserializer<LocalDate> localDateJsonDeserializer = (json, type, jsonDeserializationContext)
            -> {
        String datetime = json.getAsJsonPrimitive().getAsString();
        return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    };

    private GsonUtils() {

    }

    static {
        GsonBuilder gsonBuilder = new GsonBuilder()
                //漂亮输出
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonSerializer)
                .registerTypeAdapter(LocalDate.class, localDateJsonSerializer)
                .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonDeserializer)
                .registerTypeAdapter(LocalDate.class, localDateJsonDeserializer)
                .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
                }.getType(), new MapTypeAdapter());
        //yyyy-MM-dd HH:mm:ss
        //yyyy-MM-dd
        gson = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 获取Gson对象
     *
     * @return 返回修改后的对象，支持 Date 和 LocalDatetime和 LocalDate
     */
    public static Gson getGsonInstance() {
        return gson;
    }

    /**
     * 该方法主要功能是将Java类对象转换成json字符串
     *
     * @param o Java对象
     * @return json字符串
     */
    public static String o2J(Object o) {
        return gson.toJson(o);
    }

    /**
     * 该方法主要功能是将json字符串转换成Java类对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T j2O(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }


    /**
     * 将json字符串转化为map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

}

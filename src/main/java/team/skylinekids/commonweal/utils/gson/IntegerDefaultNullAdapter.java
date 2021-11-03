package team.skylinekids.commonweal.utils.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * 整型空字符串返回null
 *
 * @author MysticalDream
 */
public class IntegerDefaultNullAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            //定义为int类型,如果后台返回""或者null,则返回null
            if (json.getAsString().equals("") || "null".equals(json.getAsString())) {
                return null;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsInt();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
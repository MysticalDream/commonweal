package team.skylinekids.commonweal.utils.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 解决反序列化后整数变为浮点数的类型适配器
 *
 * @author MysticalDream
 */
public class MapTypeAdapter extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter jsonWriter, Object o) {

    }

    @Override
    public Object read(JsonReader jsonReader) throws IOException {
        JsonToken token = jsonReader.peek();
        switch (token) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList<>();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    list.add(read(jsonReader));
                }
                jsonReader.endArray();
                return list;

            case BEGIN_OBJECT:
                Map<String, Object> map = new LinkedTreeMap<>();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    map.put(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return map;

            case STRING:
                return jsonReader.nextString();

            case NUMBER:
                //将其作为一个字符串读取出来
                //int类型会变double类型主要是读取时是String,然后作为Number处理成Double
                //所以在这里加上判断
                String numberStr = jsonReader.nextString();
                //返回的numberStr不会为null
                if (numberStr.contains(".") || numberStr.contains("e")
                        || numberStr.contains("E")) {
                    return Double.parseDouble(numberStr);
                }
                //return Long.parseLong(numberStr);
                return Integer.parseInt(numberStr);
            case BOOLEAN:
                return jsonReader.nextBoolean();

            case NULL:
                jsonReader.nextNull();
                return null;

            default:
                throw new IllegalStateException();
        }
    }
}

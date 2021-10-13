package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.util.List;
import java.util.Map;

class GsonUtilsTest {

    @Test
    void getGsonInstance() {
    }

    @Test
    void o2J() {
    }

    @Test
    void j2O() {
    }

    @Test
    void jsonToMap() {
        Map<String, Object> map = GsonUtils.jsonToMap("{\"username\":\"common\",\"password1\":\"1234567\",\"password2\":\"12345678\",\"data\":[1.0,1.0,1.0,1]}");
        for (Map.Entry<String, Object> entry :
                map.entrySet()) {
            System.out.println("Key:" + entry.getKey() + ",Value:" + entry.getValue());
            if (entry.getKey().equals("data")) {
                for (Object o :
                        (List) entry.getValue()) {
                    System.out.println(o.getClass());
                }
            }
        }
    }
}
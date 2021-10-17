package team.skylinekids.commonweal.utils;

import team.skylinekids.commonweal.enums.NumberScope;

import java.util.HashMap;
import java.util.Map;

/**
 * 人数范围工具类
 *
 * @author MysticalDream
 */
public class ScopeUtils {
    private static Map<Integer, String> map = new HashMap<>(8);

    static {
        for (NumberScope scope : NumberScope.values()) {
            map.put(scope.getCode(), scope.getScope());
        }
    }

    public static String getScopeByNum(Integer num) {
        return map.get(num);
    }
}

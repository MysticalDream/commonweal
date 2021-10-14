package team.skylinekids.commonweal.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目分类相关工具
 *
 * @author MysticalDream
 */
public class CategoryUtils {
    private static Map<Integer, String> categoryMap = new ConcurrentHashMap<>();

    static {
        putCategory(1, "测试分类1");
        putCategory(2, "测试分类2");
        putCategory(3, "测试分类3");
        putCategory(4, "测试分类4");
        putCategory(5, "测试分类5");
    }

    /**
     * 获取分类对应的描述
     *
     * @param id
     * @return
     */
    public static String getCategoryNameById(int id) {
        return categoryMap.get(id);
    }

    /**
     * 添加分类映射
     *
     * @param id
     * @param name
     */
    public static void putCategory(int id, String name) {
        categoryMap.putIfAbsent(id, name);
    }
}

package team.skylinekids.commonweal.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目分类相关工具
 * 拙略的解决方法
 *
 * @author MysticalDream
 */
public class CategoryUtils {

    private static Map<String, Integer> categoryMap = new ConcurrentHashMap<>();

    static {
        String[] s = "社区服务 卫生健康 法律服务 环境保护 科技科普 文化艺术  交通引导 志愿消防 禁毒宣传 海外志愿服务 体育健身".split(" ");
        for (int i = 1; i <= s.length; i++) {
            putCategory(s[i - 1], i);
        }
    }

    /**
     * 获取分类的id
     *
     * @param name
     * @return
     */
    public static int getCategoryIdByName(String name) {
        return categoryMap.get(name);
    }

    /**
     * 添加分类映射
     *
     * @param id
     * @param name
     */
    public static void putCategory(String name, int id) {
        categoryMap.putIfAbsent(name, id);
    }
}

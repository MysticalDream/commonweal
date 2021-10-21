package team.skylinekids.commonweal.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目分类相关工具
 * 拙略的解决方法
 *
 * @author MysticalDream
 */
public class CategoryUtils {

    private static Map<String, Integer> categoryIdMap = new HashMap<>();

    private static Map<Integer, String> categoryNameMap = new HashMap<>();

    static {
        String[] s = "社区服务 卫生健康 法律服务 环境保护 科技科普 文化艺术  交通引导 志愿消防 禁毒宣传 海外志愿服务 体育健身".split(" ");
        for (int i = 1; i <= s.length; i++) {
            putCategoryIdMap(s[i - 1], i);
            putCategoryNameMap(i, s[i - 1]);
        }
    }

    /**
     * 根据id找分类描述
     *
     * @param id
     * @return
     */
    public static String getCategoryNameById(int id) {
        return categoryNameMap.get(id);
    }

    /**
     * 获取分类的id
     *
     * @param name
     * @return
     */
    public static Integer getCategoryIdByName(String name) {
        return categoryIdMap.get(name);
    }

    /**
     * 添加分类映射
     *
     * @param id
     * @param name
     */
    public static void putCategoryIdMap(String name, int id) {
        categoryIdMap.putIfAbsent(name, id);
    }

    /**
     * 添加分类映射
     *
     * @param id
     * @param name
     */
    public static void putCategoryNameMap(int id, String name) {
        categoryNameMap.putIfAbsent(id, name);
    }
}

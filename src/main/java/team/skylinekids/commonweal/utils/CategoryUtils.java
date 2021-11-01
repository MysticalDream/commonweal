package team.skylinekids.commonweal.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 项目分类相关工具
 * 拙略的解决方法
 *
 * @author MysticalDream
 */
public class CategoryUtils {

    private final static Logger logger = Logger.getLogger(CategoryUtils.class);

    private static Map<String, Integer> categoryIdMap = new HashMap<>();

    private static Map<Integer, String> categoryNameMap = new HashMap<>();

    static {
        try {
            loadProperties();
        } catch (IOException e) {
            logger.error("分类配置加载失败", e);
        }
    }

    public static void loadProperties() throws IOException {
        logger.info("正在加载分类配置文件");
        InputStream resourceAsStream = CategoryUtils.class.getClassLoader().getResourceAsStream("classification.properties");
        //输入流读者
        InputStreamReader reader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(reader);
        Properties properties = new Properties();
        properties.load(bufferedReader);
        String sort = properties.getProperty("sort");
        String[] s = sort.split(",");
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

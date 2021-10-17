package team.skylinekids.commonweal.utils;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 填充javaBean的工具类
 *
 * @author MysticalDream
 */
public class FillBeanUtils {

    private static final Logger logger = Logger.getLogger(FillBeanUtils.class);

    /**
     * 默认构造器私有化
     */
    private FillBeanUtils() {

    }

    /**
     * 填充javaBean对象数据方法
     *
     * @param map   数据map
     * @param clazz 需要填充的对象的Class
     * @param <T>   对象类型
     * @return
     */
    public static <T> T fill(Map<String, String[]> map, Class<T> clazz) {
        Gson gson = GsonUtils.getGsonInstance();
        try {
            T obj = clazz.getConstructor().newInstance();
            map.forEach((key, value) -> {
                //拼接出set方法
                String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                try {
                    if (value[0] == null) {
                        return;
                    }
                    Class<?> fieldClass = clazz.getDeclaredField(key).getType();

                    Object o = gson.fromJson(value[0], fieldClass);

                    Method declaredMethod = clazz.getDeclaredMethod(methodName, fieldClass);

                    if (declaredMethod != null) {
                        declaredMethod.invoke(obj, o);
                    }
                } catch (Exception e) {
                    return;
                }
            });
            return obj;
        } catch (Exception e) {
            logger.debug("填充Bean异常", e);
            return null;
        }
    }
}

package team.skylinekids.commonweal.utils;

import team.skylinekids.commonweal.pojo.query.BaseCondition;
import team.skylinekids.commonweal.utils.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 条件查询使用的工具
 *
 * @author MysticalDream
 */
public final class ConditionUtils {

    private ConditionUtils() {

    }

    public static Map<String, List<?>> collectionConditionToMap(BaseCondition baseCondition) {

        Field[] allFields = ReflectUtils.getAllFields(baseCondition);

        Map<String, List<?>> conditionMap = new HashMap<>(allFields.length);

        for (Field field : allFields) {
            System.out.println(field.getName());
        }
        return null;
    }

}

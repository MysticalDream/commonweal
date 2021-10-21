package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.utils.ConditionUtils;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConditionUtilsTest {

    @Test
    void collectionConditionToMap() {
        Map<String, List<?>> stringListMap = ConditionUtils.collectionConditionToMap(new ItemCondition());
    }
}
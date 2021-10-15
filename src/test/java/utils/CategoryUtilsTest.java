package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.CategoryUtils;

class CategoryUtilsTest {

    @Test
    void getCategoryNameById() {
        int id = CategoryUtils.getCategoryIdByName("社区服务");
        System.out.println(id);
    }

    @Test
    void putCategory() {

    }
}
package justtest;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.pojo.query.ItemCondition;
import team.skylinekids.commonweal.utils.SqlUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.util.List;

public class NotNullTest {
    @Test
    void test() {
        ItemCondition itemCondition = GsonUtils.j2O("{\n" +
                "\t\"province\": \"44\",\n" +
                "\t\"city\": \"01\",\n" +
                "\t\"itemCategory\": \"社区服务\",\n" +
                "\t\"numberScope\": \"1\",\n" +
                "\t\"pageSize\": \"9\",\n" +
                "\t\"pageNum\": \"1\"\n" +
                "}", ItemCondition.class);
        List<Object> valueList = SqlUtils.getNotNullValueList(itemCondition);
        valueList.forEach(System.out::println);
    }
}

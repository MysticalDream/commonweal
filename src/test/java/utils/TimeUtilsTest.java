package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.enums.MyTimeUnit;
import team.skylinekids.commonweal.utils.TimeUtils;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    void countHours() {
        Long aLong = TimeUtils.countHours(1, MyTimeUnit.MONTH);
        System.out.println(aLong);
    }

    @Test
    void test() {
        Long hours = TimeUtils.countHours(3, MyTimeUnit.HOUR);
        String descriptive = TimeUtils.getDescriptiveByAmount(hours);
        System.out.println(descriptive);
    }
}
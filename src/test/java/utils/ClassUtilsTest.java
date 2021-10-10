package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.ClassUtils;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilsTest {

    @Test
    void getClasses() {
        ClassUtils.getClasses("team.skylinekids.commonweal.web.controller");
    }
}
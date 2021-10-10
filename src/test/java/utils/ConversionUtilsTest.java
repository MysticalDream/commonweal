package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.utils.ConversionUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ConversionUtilsTest {

    @Test
    void convert() {
        System.out.println(new File("/upload/images/avatar/1.png").getName());
    }
}
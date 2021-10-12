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

    }

    @Test
    void Test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("wcj");
        userDTO.setUserId(1);
        userDTO.setTel("12313");
        userDTO.setAvatarUrl("/213/1312/");
        userDTO.setLocation("北京");
        System.out.println(ConversionUtils.oToStringMap(userDTO));
    }
}
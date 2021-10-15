package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.pojo.dto.UserDTO;
import team.skylinekids.commonweal.utils.convert.ConversionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    void MyTest() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("1","2","3"));
        String s = ConversionUtils.listToStringWithSeparator(list, ",");
        List<String> list1 = ConversionUtils.stringWithSeparatorToList(s, ",");
        System.out.println(s);
        System.out.println("----------------------");
        list1.forEach((e)->{
            System.out.println(e);
        });
    }
}
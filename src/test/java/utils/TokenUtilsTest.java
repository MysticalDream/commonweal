package utils;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.TokenUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenUtilsTest {

    @Test
    void getToken() {
        String token = TokenUtils.getToken();
        System.out.println(token);
    }
}
package nulltest;

import org.junit.jupiter.api.Test;

public class Test1 {
    @Test
    void Test() {
        String s = "/user/login/1";
        String a = s.substring(s.lastIndexOf("/") + 1);
        System.out.println(a);
    }
}

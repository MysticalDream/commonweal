package justtest;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.enums.NumberScope;

class NumberScopeTest {

    @Test
    void getScope() {
        NumberScope[] values = NumberScope.values();
        for (NumberScope numberScope:
             values) {
            System.out.println(numberScope.getScope());
        }
    }

    @Test
    void getCode() {
    }
}
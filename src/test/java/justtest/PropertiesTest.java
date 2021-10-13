package justtest;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.utils.gson.GsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesTest {
    @Test
    void test() throws IOException {
        Properties properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        properties.load(resourceAsStream);
        Object url = properties.get("url");
        System.out.println(Arrays.toString(GsonUtils.j2O((String) url,String[].class)));
    }
}

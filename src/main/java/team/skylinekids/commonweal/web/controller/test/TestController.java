package team.skylinekids.commonweal.web.controller.test;

import com.mysql.cj.util.DnsSrv;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author MysticalDream
 */
public class TestController {
    @MyRequestPath(value = "/send/json")
    public String test(HttpInfoWrapper httpInfoWrapper) throws IOException {
        String jsonString = httpInfoWrapper.getJsonString();
        String path = "D:/data.json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
        return jsonString;
    }
}

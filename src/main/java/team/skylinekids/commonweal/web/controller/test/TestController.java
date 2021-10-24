package team.skylinekids.commonweal.web.controller.test;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author MysticalDream
 */
public class TestController {
    @MyRequestPath(value = "/send/json")
    public String test(HttpInfoWrapper httpInfoWrapper) throws IOException {
        String jsonString = httpInfoWrapper.getJsonString();
        String path = "D:/data2.json";
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
        return jsonString;
    }

    @MyRequestPath(value = "/send/iframe", type = {RequestMethod.POST})
    public String test2(HttpInfoWrapper httpInfoWrapper) throws Exception {
        return ResultUtils.getResult(ApiResultCode.SUCCESS, LocalDateTime.now());
    }

    @MyRequestPath(value = "/json", type = {RequestMethod.GET})
    public String test3(HttpInfoWrapper httpInfoWrapper) {
        return httpInfoWrapper.getParameter("json");
    }
}

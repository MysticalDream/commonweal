package team.skylinekids.commonweal.web.controller.test;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @MyRequestPath(value = "/translate", type = {RequestMethod.GET})
    public String test4(HttpInfoWrapper httpInfoWrapper) throws IOException {
        String uri = "https://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + URLEncoder.encode(httpInfoWrapper.getParameter("s"),StandardCharsets.UTF_8) ;
        URL url = new URL(uri);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setReadTimeout(1000 * 3000);
        urlConnection.setRequestProperty("accept", "*/*");
        urlConnection.setRequestProperty("connection", "Keep-Alive");
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");
        urlConnection.connect();
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (String key :
                headerFields.keySet()) {
            System.out.println(key + "--->" + headerFields.get(key));
        }
        String result = "";
        String line;

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        while ((line = in.readLine()) != null) {
            result += line;
        }
        return result;
    }
}

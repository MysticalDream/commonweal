package team.skylinekids.commonweal.web.controller.test;

import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestMethod;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.pojo.po.Province;
import team.skylinekids.commonweal.service.ProvinceService;
import team.skylinekids.commonweal.utils.FileUtils;
import team.skylinekids.commonweal.utils.ResultUtils;
import team.skylinekids.commonweal.utils.gson.GsonUtils;
import team.skylinekids.commonweal.web.core.annotation.MyRequestPath;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MysticalDream
 */
public class TestController {
    ProvinceService provinceService = ServiceFactory.getProvinceService();

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
        String uri = "https://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + URLEncoder.encode(httpInfoWrapper.getParameter("s"), StandardCharsets.UTF_8);
        URLConnection urlConnection = getConnection(uri);
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


    @MyRequestPath(value = "/test5")
    public String test5(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String uri = "https://www.gdzyz.cn/api/mission/list.do?flag4Cashe=0&pageIndex=1&pageSize=1000";
        URLConnection urlConnection = getConnection(uri);
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
        Map<String, Object> stringObjectMap = GsonUtils.jsonToMap(result);
        Map<String, Object> a = (Map<String, Object>) stringObjectMap.get("data");
        ArrayList<Map> records = (ArrayList<Map>) a.get("records");
        ArrayList<Entity> imgList = new ArrayList<>(1000);
        for (Map<String, Object> record :
                records) {
            String missionImgS = (String) record.get("missionImgS");
            imgList.add(new Entity((String) record.get("subject")));
//            if (missionImgS.contains("upload")) {
//                String fileName = FileUtils.getFileName(missionImgS);
//                URLConnection connection = getConnection("http://image.izyz.org/obs-izy" + missionImgS);
//                //D:\commonweal\images\temp\recruit
//                File file = new File("D:/commonweal/images/temp/recruit/" + fileName);
//                FileUtils.copyFile(connection.getInputStream(), new FileOutputStream(file));
//                imgList.add(new Entity(fileName));
//                Thread.sleep(10);
//            }
        }
        return GsonUtils.o2J(imgList);
    }

    private URLConnection getConnection(String uri) throws IOException {
        URL url = new URL(uri);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setReadTimeout(1000 * 3000);
        urlConnection.setRequestProperty("accept", "*/*");
        urlConnection.setRequestProperty("connection", "Keep-Alive");
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");
        urlConnection.connect();
        return urlConnection;
    }
}

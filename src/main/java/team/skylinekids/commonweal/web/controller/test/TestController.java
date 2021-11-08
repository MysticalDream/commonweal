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
        String path = "D:/ani.json";
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
        String uri = "https://www.gdzyz.cn/api/mission/list.do?pageIndex=2&pageSize=200";
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
            // imgList.add(new Entity((String) record.get("subject")));
            if (missionImgS.contains("upload")) {
                String fileName = FileUtils.getFileName(missionImgS);
                URLConnection connection = getConnection("http://image.izyz.org/obs-izy" + missionImgS);
                //D:\commonweal\images\temp\recruit
                File file = new File("D:/commonweal/images/temp/recruit/" + fileName);
                FileUtils.copyFile(connection.getInputStream(), new FileOutputStream(file));
                imgList.add(new Entity(fileName));
                Thread.sleep(10);
            }
        }
        return GsonUtils.o2J(imgList);
    }

    @MyRequestPath(value = "/test6")
    public String test6(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String[] pics = {"http://alifei03.cfp.cn/creative/vcg/veer/612/veer-146784842.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-137009946.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-134977969.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-159298275.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-156697659.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-154249985.jpg", "http://alifei03.cfp.cn/creative/vcg/veer/612/veer-153670682.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-141503013.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-315337639.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-126898514.jpg", "http://alifei03.cfp.cn/creative/vcg/veer/612/veer-307494322.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-167573430.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-133602260.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-307700980.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-305204398.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-303813233.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-164589176.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-134600009.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-125734190.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-169244955.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-154810753.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-141161723.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-121773135.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-169109283.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-142057258.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-131704789.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-123270580.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-307835266.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-167688548.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-164967086.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-164591245.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-142515369.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-149478653.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-133521057.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-132807717.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-302426819.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-155079111.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-171244250.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-153553194.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-135952249.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-132419068.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-153121519.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-304567125.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-142422038.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-141944996.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-138885361.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-136878688.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-134522054.jpg", "http://alifei03.cfp.cn/creative/vcg/veer/612/veer-168083922.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-157904566.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-147038814.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-133789677.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-132831735.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-309929711.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-143536527.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-135492263.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-132739179.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-305177025.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-167330911.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-154846139.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-168750103.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-151477011.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-126480275.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-168942273.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-159370937.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-158410614.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-157111520.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-147072741.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-141501181.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-133155796.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-135166408.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-131860495.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-311268966.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-300831930.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-169377015.jpg", "http://tenfei02.cfp.cn/creative/vcg/veer/612/veer-137564076.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-133771821.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-133099750.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-127517027.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-308020544.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-305394053.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-160707167.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-132543688.jpg", "http://alifei03.cfp.cn/creative/vcg/veer/612/veer-122683902.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-313746793.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-310224468.jpg", "http://alifei03.cfp.cn/creative/vcg/veer/612/veer-303007062.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-300386228.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-168094409.jpg", "http://alifei05.cfp.cn/creative/vcg/veer/612/veer-160783374.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-142024531.jpg", "http://alifei03.cfp.cn/creative/vcg/veer/612/veer-141595802.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-139056153.jpg", "http://alifei04.cfp.cn/creative/vcg/veer/612/veer-308983683.jpg", "http://tenfei01.cfp.cn/creative/vcg/veer/612/veer-171358335.jpg", "http://alifei01.cfp.cn/creative/vcg/veer/612/veer-170592300.jpg", "http://tenfei04.cfp.cn/creative/vcg/veer/612/veer-169587358.jpg", "http://tenfei05.cfp.cn/creative/vcg/veer/612/veer-148342399.jpg", "http://tenfei03.cfp.cn/creative/vcg/veer/612/veer-309571987.jpg", "http://alifei02.cfp.cn/creative/vcg/veer/612/veer-307579141.jpg"};
        ArrayList<Entity> imgList = new ArrayList<>(1000);
        for (String pic :
                pics) {
            String fileName = FileUtils.getFileName(pic);
            String randomFileName = FileUtils.getRandomFileName(fileName);
            URLConnection connection = getConnection(pic);
            //D:\commonweal\images\temp\recruit
            File file = new File("D:\\commonweal\\images\\temp\\farmer\\" + randomFileName);
            FileUtils.copyFile(connection.getInputStream(), new FileOutputStream(file));
            imgList.add(new Entity(randomFileName));
            Thread.sleep(10);
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

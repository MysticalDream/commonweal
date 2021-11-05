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

    @MyRequestPath(value = "/test6")
    public String test6(HttpInfoWrapper httpInfoWrapper) throws Exception {
        String[] pics = {"https://pic.quanjing.com/7r/ma/QJ6634417836.jpg", "https://pic.quanjing.com/f8/in/QJ6501672996.jpg", "https://pic.quanjing.com/xk/eh/QJ6600059454.jpg", "https://pic.quanjing.com/wr/6v/QJ6645649084.jpg", "https://pic.quanjing.com/7c/me/QJ6375156972.jpg", "https://pic.quanjing.com/7p/70/QJ6636974700.jpg", "https://pic.quanjing.com/z9/ke/QJ6933005965.jpg", "https://pic.quanjing.com/b1/xs/QJ8196749782.jpg", "https://pic.quanjing.com/ha/17/QJ8189786185.jpg", "https://pic.quanjing.com/mx/br/QJ8108301259.jpg", "https://pic.quanjing.com/jd/gh/QJ6769933533.jpg", "https://pic.quanjing.com/vc/ph/QJ6454299897.jpg", "https://pic.quanjing.com/te/dl/QJ9100139351.jpg", "https://pic.quanjing.com/px/xp/QJ9100196819.jpg", "https://pic.quanjing.com/c8/fk/QJ9101152295.jpg", "https://pic.quanjing.com/e8/fk/QJ9101152314.jpg", "https://pic.quanjing.com/15/9i/QJ9122960142.jpg", "https://pic.quanjing.com/ps/07/QJ9123020819.jpg", "https://pic.quanjing.com/zx/2e/QJ9123491789.jpg", "https://pic.quanjing.com/z7/ca/QJ9126878605.jpg", "https://pic.quanjing.com/ex/nh/QJ6973341658.jpg", "https://pic.quanjing.com/tn/26/QJ6518074871.jpg", "https://pic.quanjing.com/1u/2j/QJ8142122158.jpg", "https://pic.quanjing.com/jd/kx/QJ6591300829.jpg", "https://pic.quanjing.com/92/81/QJ8135313268.jpg", "https://pic.quanjing.com/nx/j6/QJ8145467343.jpg", "https://pic.quanjing.com/2d/km/QJ6413468891.jpg", "https://pic.quanjing.com/tc/b7/QJ9102055671.jpg", "https://pic.quanjing.com/63/b7/QJ9102055683.jpg", "https://pic.quanjing.com/3c/b7/QJ9102055656.jpg", "https://pic.quanjing.com/1c/b7/QJ9102055662.jpg", "https://pic.quanjing.com/pd/b7/QJ9102055635.jpg", "https://pic.quanjing.com/la/fk/QJ9101152338.jpg", "https://pic.quanjing.com/pv/19/QJ9124330291.jpg", "https://pic.quanjing.com/2l/jk/QJ9125295707.jpg", "https://pic.quanjing.com/u6/fk/QJ9101152357.jpg", "https://pic.quanjing.com/76/fk/QJ9101152364.jpg", "https://pic.quanjing.com/26/fk/QJ9101152379.jpg", "https://pic.quanjing.com/sf/fk/QJ9101152384.jpg", "https://pic.quanjing.com/rz/je/QJ9123493301.jpg", "https://pic.quanjing.com/hz/je/QJ9123493289.jpg", "https://pic.quanjing.com/n2/7r/QJ9129603951.jpg", "https://pic.quanjing.com/x2/7r/QJ9129603966.jpg", "https://pic.quanjing.com/aw/7r/QJ9129603970.jpg", "https://pic.quanjing.com/nw/7r/QJ9129603983.jpg", "https://pic.quanjing.com/gw/7r/QJ9129603999.jpg", "https://pic.quanjing.com/uj/7r/QJ9129604005.jpg", "https://pic.quanjing.com/1j/7r/QJ9129604014.jpg", "https://pic.quanjing.com/bj/7r/QJ9129604022.jpg", "https://pic.quanjing.com/fx/7r/QJ9129604036.jpg", "https://pic.quanjing.com/gc/ws/QJ9105862911.jpg", "https://pic.quanjing.com/b3/ws/QJ9105862934.jpg", "https://pic.quanjing.com/r1/vs/QJ8134878677.jpg", "https://pic.quanjing.com/80/1b/QJ9125444097.jpg", "https://pic.quanjing.com/d0/1b/QJ9125444102.jpg", "https://pic.quanjing.com/00/1b/QJ9125444112.jpg", "https://pic.quanjing.com/jm/6p/QJ6835244413.jpg", "https://pic.quanjing.com/g0/v8/QJ6978332191.jpg", "https://pic.quanjing.com/56/d6/QJ6609279096.jpg", "https://pic.quanjing.com/8e/lt/QJ6876285761.jpg", "https://pic.quanjing.com/m7/mh/QJ6343142795.jpg", "https://pic.quanjing.com/uz/5j/QJ6292431269.jpg", "https://pic.quanjing.com/9z/rk/QJ6430447028.jpg", "https://pic.quanjing.com/bs/tw/QJ9109920790.jpg", "https://pic.quanjing.com/ll/23/QJ6897823314.jpg", "https://pic.quanjing.com/hn/5x/QJ9122570729.jpg", "https://pic.quanjing.com/mn/5x/QJ9122570731.jpg", "https://pic.quanjing.com/wn/5x/QJ9122570748.jpg", "https://pic.quanjing.com/i8/9x/QJ9126760490.jpg", "https://pic.quanjing.com/gv/ij/QJ6198045503.jpg", "https://pic.quanjing.com/18/fk/QJ9101152302.jpg", "https://pic.quanjing.com/pf/xt/QJ8104179859.jpg", "https://pic.quanjing.com/vs/bj/QJ6288234521.jpg", "https://pic.quanjing.com/8a/pm/QJ6103092289.jpg", "https://pic.quanjing.com/l1/je/QJ9123493330.jpg", "https://pic.quanjing.com/2w/ue/QJ6145513371.jpg", "https://pic.quanjing.com/d6/bx/QJ6835624038.jpg", "https://pic.quanjing.com/vn/58/QJ6858793465.jpg", "https://pic.quanjing.com/gl/vi/QJ6522496607.jpg", "https://pic.quanjing.com/t6/17/QJ6589659255.jpg", "https://pic.quanjing.com/p8/91/QJ6456561715.jpg", "https://pic.quanjing.com/ar/vr/QJ6198847138.jpg"};
        ArrayList<Entity> imgList = new ArrayList<>(1000);
        for (String pic :
                pics) {
            String fileName = FileUtils.getFileName(pic);
            URLConnection connection = getConnection(pic);
            //D:\commonweal\images\temp\recruit
            File file = new File("D:\\commonweal\\images\\temp\\adopt\\" + fileName);
            FileUtils.copyFile(connection.getInputStream(), new FileOutputStream(file));
            imgList.add(new Entity(fileName));
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

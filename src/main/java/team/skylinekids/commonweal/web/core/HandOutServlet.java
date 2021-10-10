package team.skylinekids.commonweal.web.core;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
import team.skylinekids.commonweal.enums.RequestType;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;
import team.skylinekids.commonweal.utils.ClassUtils;
import team.skylinekids.commonweal.utils.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author MysticalDream
 */
@WebServlet(urlPatterns = {"*.do"}, loadOnStartup = 0)
@MultipartConfig
public class HandOutServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(HandOutServlet.class);

    @Override
    public void init() throws ServletException {
        Set<Class<?>> classSet = ClassUtils.getClasses("team.skylinekids.commonweal.web.controller");
        //处理注解和收集路径和方法映射
        HandlerMapping.handler(classSet);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DispatcherServlet running");
        //处理请求
        doDispatch(req, resp);
    }

    /**
     * 处理请求
     *
     * @param request
     * @param response
     */
    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求路径 比如/test/login.do
        String uri = request.getRequestURI();

        int index = uri.indexOf(".");

        if (index == -1) {
            logger.debug("404:资源不存在");
            response.getWriter().write(ResultUtils.getResult(ApiResultCode.THE_PAGE_NOT_FOUND));
            return;
        }
        //去掉后缀后/test/login
        uri = uri.substring(0, index);
        MyMethodInfo myMethodInfo = HandlerMapping.get(uri);
        //资源不存在
        if (myMethodInfo == null) {
            logger.debug("404:资源不存在");
            response.getWriter().write(ResultUtils.getResult(ApiResultCode.THE_PAGE_NOT_FOUND));
            return;
        }
        /**
         * 判断是否支持该请求方法
         */
        boolean supportable = judgeRequestMethod(request, myMethodInfo.getRequestTypes());
        if (!supportable) {
            response.getWriter().write(ResultUtils.getResult(ApiResultCode.REQUEST_METHOD_NOT_ALLOWED));
            logger.debug("405:请求方法不允许");
            return;
        }

        Object object = myMethodInfo.getObject();

        Method method = myMethodInfo.getMethod();
        Object invoke = null;

        try {
            invoke = method.invoke(object, new HttpInfoWrapper(response, request));
        } catch (Exception e) {
            logger.error("方法调用异常:" + e.getMessage(), e);
        }
        //返回值不为空时
        if (invoke != null) {
            //响应json
            if (invoke instanceof String) {
                response.setContentType("application/json");
                response.getWriter().write((String) invoke);
            }
            //TODO 优化流
            //以流的形式响应回去
            if (invoke instanceof InputStream) {
                InputStream inputStream = (InputStream) invoke;
                ServletOutputStream outputStream = response.getOutputStream();
                //判断输入流中的数据是否已经读完的标识
                int len = 0;
                //创建一个缓冲区
                byte[] buff = new byte[1024];
                //循环将输入流读入到缓冲区
                while ((len = inputStream.read(buff)) > 0) {
                    outputStream.write(buff, 0, len);
                }
                //关闭资源
                outputStream.close();
                inputStream.close();
            }

        }

    }

    /**
     * 判断是否支持该方法请求方法
     *
     * @param request
     * @param types
     * @return
     */
    private boolean judgeRequestMethod(HttpServletRequest request, RequestType[] types) {
        //例如：GET
        String method = request.getMethod();
        for (RequestType requestType :
                types) {
            if (requestType.getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }
}

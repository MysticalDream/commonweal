package team.skylinekids.commonweal.web.core;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.enums.ApiResultCode;
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
@WebServlet(urlPatterns = {"/"}, loadOnStartup = 0)
@MultipartConfig
public class HandOutServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(HandOutServlet.class);

    @Override
    public void init() {
        Set<Class<?>> classSet = ClassUtils.getClasses("team.skylinekids.commonweal.web.controller");
        //处理注解和收集路径和方法映射
        HandlerMapping.handle(classSet);
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
    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取请求路径 比如/users/1
        String uri = request.getRequestURI();

        String pathVariable = null;

        HandleInfo handleInfo = HandlerMapping.get(uri);

        //如果找不到该方法,将url路径的最后一个路径参数变为?再次看看有木有
        if (handleInfo == null) {
            //路径参数的开始下标
            int i = uri.lastIndexOf("/") + 1;
            pathVariable = "".equals(uri.substring(i).trim()) ? null : uri.substring(i).trim();
            //如果没有参数直接去掉最后的 /
            if (pathVariable == null) {
                uri = uri.substring(0, i - 1);
            } else {
                //拼接出带有?符号的uri
                uri = uri.substring(0, i) + "?";
            }
//            //为空交由原生Servlet处理
//            if ("".equals(uri)) {
//                super.service(request, response);
//                return;
//            }

            handleInfo = HandlerMapping.get(uri);
            // 资源不存在
            if (handleInfo == null) {
                logger.debug("404:资源不存在:" + uri);
                response.getWriter().write(ResultUtils.getResult(ApiResultCode.THE_PAGE_NOT_FOUND));
                return;
            }
        }
        /**
         * 判断是否支持该请求方法
         */
        Method method = handleInfo.getMethodByRequestType(request.getMethod());
        if (method == null) {
            response.getWriter().write(ResultUtils.getResult(ApiResultCode.REQUEST_METHOD_NOT_ALLOWED));
            logger.debug("405:请求方法不允许");
            return;
        }
        Object object = handleInfo.getObject();
        Object result = null;
        try {
            result = method.invoke(object, new HttpInfoWrapper(response, request, pathVariable));
        } catch (Exception e) {
            logger.error("方法调用异常:" + e.getMessage(), e);
            response.getWriter().write(ResultUtils.getResult(ApiResultCode.SERVER_RUNNING_EXCEPTION));
        }
        //返回值不为空时
        if (result != null) {
            //响应json
            if (result instanceof String) {
                response.setContentType("application/json");
                response.getWriter().write((String) result);
            }
            //TODO 优化流
            //以流的形式响应回去
            if (result instanceof InputStream) {
                InputStream inputStream = (InputStream) result;
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

}

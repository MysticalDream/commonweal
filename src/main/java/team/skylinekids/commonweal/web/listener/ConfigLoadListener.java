package team.skylinekids.commonweal.web.listener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import team.skylinekids.commonweal.utils.ClassUtils;
import team.skylinekids.commonweal.web.core.HandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.Set;

/**
 * 初始化log4j配置
 *
 * @author MysticalDream
 */
@WebListener
public class ConfigLoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initLog4j(sce);
    }


    /**
     * 初始化log4j配置
     *
     * @param sce
     */

    private void initLog4j(ServletContextEvent sce) {
        System.out.println("log4j init Servlet 正在初始化 log4j日志设置信息");
        ServletContext sc = sce.getServletContext();

        String log4jLocation = sc.getInitParameter("log4j-properties-location");

        if (log4jLocation == null) {
            System.err.println("*** 没有 log4j-properties-location 初始化的文件, 所以使用 BasicConfigurator初始化");
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File file = new File(log4jProp);
            if (file.exists()) {
                System.out.println("使用: " + log4jProp + "初始化日志设置信息");
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " 文件没有找到， 所以使用 BasicConfigurator初始化");
                BasicConfigurator.configure();
            }
        }
    }

}

package team.skylinekids.commonweal.web.filter;

import org.apache.log4j.Logger;
import team.skylinekids.commonweal.pojo.bo.HttpInfoWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 登录过滤
 *
 * @author MysticalDream
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    private final Logger logger = Logger.getLogger(LoginFilter.class);
    private final Set<String> urlNeedLoginSet
            = Set.of("/pages/myArea/my.html", "/mytest/p/live3.html", "/mytest/p/see2.html");
    private final Set<String> urlNeedLeaveSet = Set.of("/pages/login/come.html");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpInfoWrapper wrapper = new HttpInfoWrapper((HttpServletResponse) response, (HttpServletRequest) request, null);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        if (urlNeedLoginSet.contains(requestURI) && !wrapper.isLogin()) {
            logger.info("未登录,已跳转到登录页面");
            ((HttpServletResponse) response).sendRedirect("/pages/login/come.html");
            return;
        }
        if (urlNeedLeaveSet.contains(requestURI) && wrapper.isLogin()) {
            logger.info("已登录,跳回首页");
            ((HttpServletResponse) response).sendRedirect("/");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

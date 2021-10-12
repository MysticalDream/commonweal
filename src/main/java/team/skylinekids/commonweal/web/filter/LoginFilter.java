package team.skylinekids.commonweal.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 登录拦截验证
 *
 * @author MysticalDream
 */
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    private Logger logger = Logger.getLogger(LoginFilter.class);

    private final Set<String> AUTHORIZATION_IS_REQUIRED = Set.of("/sessions");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //TODO 先放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

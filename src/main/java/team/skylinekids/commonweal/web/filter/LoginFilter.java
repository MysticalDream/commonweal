package team.skylinekids.commonweal.web.filter;

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

    private final Set<String> urlSet = Set.of("/pages/myArea/my.html");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpInfoWrapper wrapper = new HttpInfoWrapper((HttpServletResponse) response, (HttpServletRequest) request, null);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        if (urlSet.contains(requestURI) && !wrapper.isLogin()) {
            ((HttpServletResponse) response).sendRedirect("/pages/login/come.html");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

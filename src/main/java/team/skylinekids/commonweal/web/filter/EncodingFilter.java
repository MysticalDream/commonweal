package team.skylinekids.commonweal.web.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 转换编码
 *
 * @author MysticalDream
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    private Logger logger = Logger.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
        //((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public void destroy() {

    }
}

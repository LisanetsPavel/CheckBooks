package checkbooks.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CatchErrorFilter implements Filter {

    private static final Logger logger = Logger.getLogger(CatchErrorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            chain.doFilter(request, response);
        } catch (Throwable ex) {
            ex.printStackTrace();
            logger.error(ex);
            resp.sendRedirect("/error");
        }
    }

    @Override
    public void destroy() {
    }
}

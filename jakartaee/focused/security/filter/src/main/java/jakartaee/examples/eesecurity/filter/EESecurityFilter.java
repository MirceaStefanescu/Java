package jakartaee.examples.eesecurity.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A filter example with EESecurity.
 */
@WebFilter(filterName = "EESecurityFilter", urlPatterns = {"/protected"})
public class EESecurityFilter implements Filter {

    /**
     * Destroy the filter.
     */
    @Override public void destroy() {
    }

    /**
     * Process the filter.
     *
     * @param request  the servlet request.
     * @param response the servlet response.
     * @param chain    the filter chain.
     * @throws IOException      when an I/O error occurs.
     * @throws ServletException when a servlet error occurs.
     */
    @Override public void doFilter(ServletRequest request,
                                   ServletResponse response,
                                   FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (httpServletResponse != null) {
            httpServletResponse.getWriter().print("And voila we were allowed to see this!");
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Initialize the filter.
     *
     * @param filterConfig the filter configuration.
     */
    @Override public void init(FilterConfig filterConfig) {
    }
}

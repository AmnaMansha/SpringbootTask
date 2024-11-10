import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Action before passing request to the next filter or application
        System.out.println("Before Filter - URI: " + httpRequest.getRequestURI());

        // Pass the request down the filter chain
        chain.doFilter(request, response);

        // Action after the rest of the application has processed the request
        System.out.println("After Filter - Status: " + httpResponse.getStatus());
    }

    @Override
    public void destroy() {
        // Clean-up logic, if needed
    }
}

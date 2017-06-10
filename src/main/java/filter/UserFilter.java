package filter;

import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = (User) servletRequest.getAttribute(Constants.Attributes.CURRENT_USER);

        if (user == null) {
            LOGGER.info("User not authorised");
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(401);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

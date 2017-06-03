package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter extends BaseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoCacheFilter.class);

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store");
        LOGGER.info("Caching was turned off");
        chain.doFilter(request, response);
    }
}

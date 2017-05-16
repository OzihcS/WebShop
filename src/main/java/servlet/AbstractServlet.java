package servlet;

import repository.UserRepository;
import service.CaptchaService;
import service.UserService;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Basic servlet for all servlets. Provides access to
 * services that are needed for more than one servlet.
 */
public abstract class AbstractServlet extends HttpServlet {

    private CaptchaService captchaService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        captchaService = (CaptchaService) getServletContext().getAttribute(Constants.Attributes.CAPTCHA_SERVICE);
        userService = (UserService) getServletContext().getAttribute(Constants.Attributes.USER_REPOSITORY);
    }

    /**
     * @return some implementation of {@link CaptchaService}
     */
    public CaptchaService getCaptchaService() {
        return captchaService;
    }

    /**
     * @return {@link UserRepository}
     */
    public UserService getUserService() {
        return userService;
    }

    protected void redirectToAction(String uri, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + uri);
    }

    protected void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    /**
     * Retrieves specified String parameter from request.
     *
     * @param param The name of parameter to retrieve
     * @return {@link String} value of parameter
     */
    protected String getStringParam(HttpServletRequest request, String param) {
        String paramValue = request.getParameter(param);
        if (paramValue == null) {
            return null;
        }
        return paramValue.trim();
    }

}
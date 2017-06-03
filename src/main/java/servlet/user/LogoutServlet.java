package servlet.user;

import servlet.AbstractServlet;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which provides functionality of logout.
 */
@WebServlet(urlPatterns = Constants.ServletPath.LOGOUT)
public class LogoutServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(Constants.Attributes.CURRENT_USER);
        redirectToAction(Constants.Pages.HOME, req, resp);
    }
}

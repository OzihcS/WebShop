package servlet.user;

import domain.User;
import servlet.AbstractServlet;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which provides functionality of login.
 */
@WebServlet(urlPatterns = Constants.ServletPath.LOGIN)
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.Pages.LOGIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = getStringParam(req, Constants.Attributes.User.EMAIL);
        String password = getStringParam(req, Constants.Attributes.User.PASSWORD);

        User user = getUserService().login(email, password);

        if (user != null) {
            req.getSession().setAttribute(Constants.Attributes.CURRENT_USER, user);
            redirectToAction(Constants.Pages.HOME, req, resp);
        } else {
            redirectToAction(Constants.ServletPath.LOGIN, req, resp);
        }
    }
}

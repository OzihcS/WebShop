package servlet.user;

import nl.captcha.servlet.CaptchaServletUtil;
import servlet.AbstractServlet;
import util.Constants.ServletPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which draw captcha.
 */
@WebServlet(urlPatterns = ServletPath.CAPTCHA)
public class CaptchaServlet extends AbstractServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CaptchaServletUtil.writeImage(resp, getCaptchaService().get(req));
    }
}

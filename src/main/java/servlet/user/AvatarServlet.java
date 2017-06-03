package servlet.user;

import domain.User;
import nl.captcha.servlet.CaptchaServletUtil;
import servlet.AbstractServlet;
import util.Constants;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Servlet which draw user's avatar.
 */
@WebServlet(urlPatterns = Constants.ServletPath.AVATAR)
public class AvatarServlet extends AbstractServlet {

    private static final String AVATAR_PATH = System.getProperty("tomcat.server.data.dir");
    private static final String DEFAULT_AVATAR = "username.png";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.Attributes.CURRENT_USER);

        if (user.getAvatar() == null) {
            CaptchaServletUtil.writeImage(resp, ImageIO.read(new File(AVATAR_PATH + DEFAULT_AVATAR)));
        } else {
            File avatar = new File(AVATAR_PATH + user.getAvatar());
            BufferedImage bufferedImage = ImageIO.read(avatar);

            if (bufferedImage == null) {
                bufferedImage = ImageIO.read(new File(AVATAR_PATH + DEFAULT_AVATAR));
            }

            CaptchaServletUtil.writeImage(resp, bufferedImage);
        }
    }
}

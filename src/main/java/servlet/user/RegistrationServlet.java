package servlet.user;


import DTO.UserDTO;
import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.AbstractServlet;
import util.Constants;
import util.Constants.ErrorMessages;
import util.Constants.Pages;
import util.Constants.ServletPath;
import validator.UserValidator;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.Constants.Attributes;

/**
 * Provides user registration with data and captcha validation.
 */
@WebServlet(urlPatterns = ServletPath.REGISTRATION)
@MultipartConfig
public class RegistrationServlet extends AbstractServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);
    private static final String AVATAR_PATH = System.getProperty("tomcat.server.data.dir");
    private static final String AVATAR_FILE_FORMAT = ".png";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = (HashMap) req.getSession().getAttribute(Attributes.ERRORS);
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute(Attributes.USER_DTO);

        getCaptchaService().generate(req, resp);

        req.setAttribute(Attributes.ERRORS, errors);
        req.setAttribute(Attributes.USER_DTO, userDTO);

        req.getSession().removeAttribute(Attributes.ERRORS);
        req.getSession().removeAttribute(Attributes.USER_DTO);

        forward(Pages.REGISTRATION, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = getStringParam(req, Attributes.User.FIRST_NAME);
        String lastName = getStringParam(req, Attributes.User.LAST_NAME);
        String email = getStringParam(req, Attributes.User.EMAIL);
        String password = getStringParam(req, Attributes.User.PASSWORD);
        String passwordConfirmation = getStringParam(req, Attributes.User.PASSWORD_CONFIRMATION);
        String captcha = getStringParam(req, Attributes.CAPTCHA);

        UserDTO userDTO = new UserDTO(firstName, lastName, email, password, passwordConfirmation);
        UserValidator validator = new UserValidator(userDTO);
        validator.validate();

        if (!getCaptchaService().verifyCaptcha(captcha, req)) {
            Map<String, String> errors = validator.getErrors();

            if (errors == null) {
                errors = new HashMap<>();
            }

            errors.put(Attributes.CAPTCHA, ErrorMessages.CAPTCHA_ERROR);

            req.getSession().setAttribute(Attributes.ERRORS, errors);
            req.getSession().setAttribute(Attributes.USER_DTO, userDTO);
            redirectToAction(ServletPath.REGISTRATION, req, resp);
            return;
        }

        if (validator.getErrors().isEmpty()) {
            User user = userDTO.convertToDomain();

            if (getUserService().exist(user)) {
                req.setAttribute(Constants.Attributes.ERROR_MESSAGE, ErrorMessages.USER_ALREADY_EXIST);
                forward(Pages.ERROR, req, resp);
            } else {
                if (readAvatar(req, user.getEmail())) {
                    user.setAvatar(user.getEmail() + AVATAR_FILE_FORMAT);
                }
                getUserService().add(user);
                redirectToAction(ServletPath.LOGIN, req, resp);
            }
        } else {
            req.getSession().setAttribute(Attributes.ERRORS, validator.getErrors());
            req.getSession().setAttribute(Attributes.USER_DTO, userDTO);
            redirectToAction(ServletPath.REGISTRATION, req, resp);
        }
    }

    private boolean readAvatar(HttpServletRequest req, String id) throws IOException, ServletException {
        List<Part> fileParts = req.getParts().stream().filter(part ->
                Attributes.User.AVATAR.equals(part.getName())).collect(Collectors.toList());

        File avatar = new File(AVATAR_PATH + id + AVATAR_FILE_FORMAT);
        OutputStream outStream = new FileOutputStream(avatar);

        fileParts.forEach(part -> {
            try {
                InputStream fileContent = part.getInputStream();
                byte[] buffer = new byte[fileContent.available()];
                fileContent.read(buffer);
                outStream.write(buffer);
                fileContent.close();
            } catch (IOException e) {
                LOGGER.error("Occurred exception when read avatar from stream with message: {}", e.getMessage());
                try {
                    outStream.close();
                } catch (IOException e1) {
                    LOGGER.error("Occurred exception when tried to close output stream with message: {}", e.getMessage());
                }
            }
        });

        outStream.flush();
        outStream.close();

        if (ImageIO.read(avatar) == null) {
            avatar.delete();
            return false;
        }
        return true;
    }
}

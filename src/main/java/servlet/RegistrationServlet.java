package servlet;


import DTO.UserDTO;
import domain.User;
import util.Constants;
import util.Constants.ErrorMessages;
import util.Constants.Pages;
import util.Constants.ServletPath;
import validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Attributes.*;

/**
 * Provides user registration with data and captcha validation.
 */
@WebServlet(urlPatterns = ServletPath.REGISTRATION)
public class RegistrationServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = (HashMap) req.getSession().getAttribute(ERRORS);
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute(USER_DTO);

        getCaptchaService().generate(req, resp);

        req.setAttribute(ERRORS, errors);
        req.setAttribute(USER_DTO, userDTO);

        req.getSession().removeAttribute(ERRORS);
        req.getSession().removeAttribute(USER_DTO);

        forward(Pages.REGISTRATION, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = getStringParam(req, FIRST_NAME);
        String lastName = getStringParam(req, LAST_NAME);
        String email = getStringParam(req, EMAIL);
        String password = getStringParam(req, PASSWORD);
        String passwordConfirmation = getStringParam(req, PASSWORD_CONFIRMATION);
        String captcha = getStringParam(req, CAPTCHA);

        UserDTO userDTO = new UserDTO(firstName, lastName, email, password, passwordConfirmation);
        UserValidator validator = new UserValidator(userDTO);
        validator.validate();

        if (!getCaptchaService().verifyCaptcha(captcha, req)) {
            Map<String, String> errors = validator.getErrors();

            if (errors == null) {
                errors = new HashMap<>();
            }

            errors.put(CAPTCHA, ErrorMessages.CAPTCHA_ERROR);

            req.getSession().setAttribute(ERRORS, errors);
            req.getSession().setAttribute(USER_DTO, userDTO);
            redirectToAction(ServletPath.REGISTRATION, req, resp);
            return;
        }

        if (validator.getErrors().isEmpty()) {
            User user = userDTO.convertToDomain();
            if (getUserService().contains(user)) {
                req.setAttribute(Constants.Attributes.ERROR_MESSAGE, ErrorMessages.USER_ALREADY_EXIST);
                forward(Pages.ERROR, req, resp);
            } else {
                getUserService().add(user);
                redirectToAction(Pages.LOGIN, req, resp);
            }
        } else {
            req.getSession().setAttribute(ERRORS, validator.getErrors());
            req.getSession().setAttribute(USER_DTO, userDTO);
            redirectToAction(ServletPath.REGISTRATION, req, resp);
        }
    }
}

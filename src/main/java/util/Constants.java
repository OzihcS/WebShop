package util;

/**
 * Constants storage.
 */
public class Constants {

    public static final class Attributes {
        public static final String FIRST_NAME = "first name";
        public static final String LAST_NAME = "last name";
        public static final String EMAIL = "e-mail";
        public static final String PASSWORD = "password";
        public static final String PASSWORD_CONFIRMATION = "password confirmation";
        public static final String ERRORS = "errors";
        public static final String USER_DTO = "userDTO";
        public static final String CAPTCHA = "captcha";
        public static final String CAPTCHA_SERVICE = "captchaService";
        public static final String USER_REPOSITORY = "userRepository";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String CAPTCHA_ID = "captchaID";
        public static final String HIDDEN_INPUT = "useHiddenInput";
    }

    public static final class CaptchaInitParams {
        public static final String SESSION = "session";
        public static final String SERVLET_CONTEXT = "context";
        public static final String SERVLET_CONTEXT_TYPE = "context type";
        public static final String COOKIE = "cookie";
    }

    public static final class ErrorMessages {
        public static final String FIRST_NAME_EMPTY = "First name can not be empty";
        public static final String LAST_NAME_EMPTY = "Last name can not be empty";
        public static final String EMAIL_EMPTY = "Email can not be empty";

        public static final String SHORT_PASSWORD = "Password should be more than 5 symbols";
        public static final String PASSWORDS_NOT_MATCHES = "Passwords do not matches";

        public static final String WRONG_INPUT_NAME = "Wrong input name";
        public static final String WRONG_INPUT_EMAIL = "Wrong input email";
        public static final String USER_ALREADY_EXIST = "User with this e-mail already exist.";
        public static final String CAPTCHA_ERROR = "Captcha not matches";
    }


    public static final class Pages {
        private static final String DIR = "/WEB-INF/pages";

        public static final String REGISTRATION = DIR + "/registration.jsp";
        public static final String LOGIN = "/login.html";
        public static final String ERROR = DIR + "/error.jsp";
    }

    public static final class ServletPath {
        public static final String REGISTRATION = "/registration";
        public static final String CAPTCHA = "/captcha";
    }

}

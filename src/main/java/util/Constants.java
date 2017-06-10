package util;

/**
 * Constants storage.
 */
public class Constants {

    public static final class Attributes {

        public static final String LOCALES = "locales";
        public static final String DEFAULT_LOCALE = "default_locale";
        public static final String CURRENT_LOCALE = "current_locale";

        public static final class Services {

            public static final String CART_SERVICE = "cartService";
            public static final String CAPTCHA_SERVICE = "captchaService";
            public static final String USER_SERVICE = "userService";
            public static final String PRODUCT_SERVICE = "productService";
            public static final String ORDER_SERVICE = "orderService";
        }
        public static final class User {

            public static final String ID = "id";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
            public static final String AVATAR = "avatar";
            public static final String PASSWORD_CONFIRMATION = "password confirmation";
        }
        public static final class Product {

            public static final String TABLE_NAME = "product";
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String CATEGORY = "category";
            public static final String DESCRIPTION = "description";
            public static final String MANUFACTURER = "manufacturer";
            public static final String MIN_PRICE = "min_price";
            public static final String MAX_PRICE = "max_price";
            public static final String PRICE = "price";
            public static final String GRID = "grid";
            public static final String PAGINATION = "pagination";
            public static final String ORDER = "order";
            public static final String IMAGE = "image";
        }

        public static final String LANG = "lang";
        public static final String CURRENT_PAGE = "currentPage";
        public static final String PAGES = "pages";
        public static final String PARAM_LIST = "paramList";
        public static final String ERRORS = "errors";
        public static final String USER_DTO = "userDTO";
        public static final String CAPTCHA = "captcha";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String CAPTCHA_ID = "captchaID";
        public static final String HIDDEN_INPUT = "useHiddenInput";
        public static final String CURRENT_USER = "currentUser";
        public static final String PRODUCTS = "products";
    }

    public static final class InitParams {
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
        public static final String LOGIN = DIR + "/login.jsp";
        public static final String ERROR = DIR + "/error.jsp";
        public static final String PRODUCTS = DIR + "/products.jsp";
        public static final String SINGLE = DIR + "/single.jsp";
        public static final String CART = DIR + "/cart.jsp";
        public static final String HOME = "/index.html";
        public static final String ORDER = DIR + "/order.jsp";
    }


    public static final class ServletPath {
        public static final String REGISTRATION = "/registration";
        public static final String CAPTCHA = "/captcha";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String AVATAR = "/avatar";
        public static final String PRODUCTS = "/products";
        public static final String SINGLE = "/single";
        public static final String CART = "/cart";
        public static final String MAKE_ORDER = "/makeOrder";
    }

    public static final class DBConfigurations {
        public static final String URL = "MYSQL_DB_URL";
        public static final String USER_NAME = "MYSQL_DB_USERNAME";
        public static final String PASSWORD = "MYSQL_DB_PASSWORD";
    }

}

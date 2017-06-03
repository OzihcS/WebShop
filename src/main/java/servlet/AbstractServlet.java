package servlet;

import repository.UserRepository;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.captcha.CaptchaService;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Attributes.Services.*;

/**
 * Basic servlet for all servlets. Provides access to
 * services that are needed for more than one servlet.
 */
public abstract class AbstractServlet extends HttpServlet {

    private CaptchaService captchaService;
    private UserService userService;
    private ProductService productService;
    private OrderService orderService;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        captchaService = (CaptchaService) getServletContext().getAttribute(CAPTCHA_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
        cartService = (CartService) getServletContext().getAttribute(CART_SERVICE);
    }

    public CartService getCartService() {
        return cartService;
    }

    public OrderService getOrderService() {
        return orderService;
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

    public ProductService getProductService() {
        return productService;
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
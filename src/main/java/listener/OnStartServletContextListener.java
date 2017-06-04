package listener;

import db.transaction.TransactionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.UserRepository;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.captcha.CaptchaService;
import service.captcha.ServletContextCaptchaService;
import service.captcha.SessionCaptchaService;
import util.Constants.Attributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static util.Constants.Attributes.Services.*;
import static util.Constants.InitParams.*;

/**
 * Listener which initialise {@link CaptchaService} and {@link UserRepository} with specified params from context.
 */
public class OnStartServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnStartServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String captchaStorage = servletContextEvent.getServletContext().getInitParameter(Attributes.CAPTCHA);
        TransactionHandler transactionHandler = new TransactionHandler();

        servletContextEvent.getServletContext().setAttribute(USER_SERVICE,
                new UserService(transactionHandler));
        servletContextEvent.getServletContext().setAttribute(PRODUCT_SERVICE,
                new ProductService(transactionHandler));
        servletContextEvent.getServletContext().setAttribute(ORDER_SERVICE,
                new OrderService(transactionHandler));
        servletContextEvent.getServletContext().setAttribute(CART_SERVICE, new CartService());

        LOGGER.info("Services was loaded");

        switch (captchaStorage) {
            case SESSION:
                servletContextEvent.getServletContext().setAttribute(CAPTCHA_SERVICE,
                        new SessionCaptchaService());
                LOGGER.info("Session captcha service was loaded");
                break;
            case SERVLET_CONTEXT:
                servletContextEvent.getServletContext().setAttribute(CAPTCHA_SERVICE,
                        new ServletContextCaptchaService(getServletContextCaptchaServiceInitParam(servletContextEvent)));
                LOGGER.info("Servlet context captcha service was loaded");
                break;
            default:
                servletContextEvent.getServletContext().setAttribute(CAPTCHA_SERVICE,
                        new SessionCaptchaService());
                LOGGER.warn("Default captcha service was loaded");
                break;
        }
    }

    private String getServletContextCaptchaServiceInitParam(ServletContextEvent servletContextEvent) {
        String servletContextInitParam = servletContextEvent.getServletContext().getInitParameter(SERVLET_CONTEXT_TYPE);

        if (servletContextInitParam == null || servletContextInitParam.isEmpty()) {
            return COOKIE;
        }

        return servletContextInitParam;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(CAPTCHA_SERVICE);
        servletContextEvent.getServletContext().removeAttribute(USER_SERVICE);
        servletContextEvent.getServletContext().removeAttribute(PRODUCT_SERVICE);
        servletContextEvent.getServletContext().removeAttribute(CART_SERVICE);
        servletContextEvent.getServletContext().removeAttribute(ORDER_SERVICE);
        LOGGER.info("Services were removed");
    }
}

package listener;

import repository.UserRepository;
import service.CaptchaService;
import service.ServletContextCaptchaService;
import service.SessionCaptchaService;
import service.UserService;
import util.Constants;
import util.Constants.Attributes;
import util.Constants.CaptchaInitParams;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Listener which initialise {@link CaptchaService} and {@link UserRepository} with specified params from context.
 */
public class OnStartServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String captchaStorage = servletContextEvent.getServletContext().getInitParameter(Attributes.CAPTCHA);
        servletContextEvent.getServletContext().setAttribute(Attributes.USER_REPOSITORY,
                new UserService());

        if (captchaStorage.equals(CaptchaInitParams.SESSION)) {
            servletContextEvent.getServletContext().setAttribute(Attributes.CAPTCHA_SERVICE,
                    new SessionCaptchaService());
        } else if (captchaStorage.equals(CaptchaInitParams.SERVLET_CONTEXT)) {
            servletContextEvent.getServletContext().setAttribute(Attributes.CAPTCHA_SERVICE,
                    new ServletContextCaptchaService(getServletContextCaptchaServiceInitParam(servletContextEvent)));
        } else {
            servletContextEvent.getServletContext().setAttribute(Attributes.CAPTCHA_SERVICE,
                    new SessionCaptchaService());
        }
    }

    private String getServletContextCaptchaServiceInitParam(ServletContextEvent servletContextEvent) {
        String servletContextInitParam = servletContextEvent.getServletContext().getInitParameter(CaptchaInitParams.SERVLET_CONTEXT_TYPE);

        if (servletContextInitParam == null || servletContextInitParam.isEmpty()) {
            return CaptchaInitParams.COOKIE;
        }

        return servletContextInitParam;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(Attributes.CAPTCHA_SERVICE);
        servletContextEvent.getServletContext().removeAttribute(Attributes.USER_REPOSITORY);
    }
}

package service.captcha;

import util.CaptchaWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Base captcha service class that provides basic methods.
 */
public abstract class CaptchaService {

    /**
     * @return new object ow captcha wrapper.
     */
    protected CaptchaWrapper create() {
        return new CaptchaWrapper();
    }

    /**
     * Generate captcha and put it to captcha storage.
     *
     * @param request  servlet request.
     * @param response servlet response.
     */
    public abstract void generate(HttpServletRequest request, HttpServletResponse response);

    /**
     * Gets captcha image.
     *
     * @param request place where exist captcha id to verify.
     * @return captcha image.
     */
    public abstract BufferedImage get(HttpServletRequest request);

    /**
     * Provides captcha verification.
     *
     * @param answer  users answer to verify with captcha.
     * @param request place where exist captcha id to verify.
     * @return true if user's answer is correct, otherwise false.
     */
    public abstract boolean verifyCaptcha(String answer, HttpServletRequest request);

}

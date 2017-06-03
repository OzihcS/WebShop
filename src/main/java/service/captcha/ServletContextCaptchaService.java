package service.captcha;

import util.CaptchaWrapper;
import util.Constants.Attributes;
import util.Constants.CaptchaInitParams;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServletContextCaptchaService extends CaptchaService {

    private String storage;
    private Map<String, CaptchaWrapper> captchas;
    private static final int SESSION_MAX_LIFE_TIME = 15;

    public ServletContextCaptchaService(String storage) {
        this.captchas = new ConcurrentHashMap<>();
        ScheduledExecutorService invalidator = Executors.newScheduledThreadPool(1,
                factory -> {
                    Thread t = Executors.defaultThreadFactory().newThread(factory);
                    t.setDaemon(true);
                    return t;
                });
        invalidator.scheduleWithFixedDelay(this::invalidate, 1, 1, TimeUnit.MINUTES);
        this.storage = storage;
    }

    @Override
    public void generate(HttpServletRequest request, HttpServletResponse response) {
        String id = UUID.randomUUID().toString();
        CaptchaWrapper captcha = create();
        captchas.put(id, captcha);

        if (storage.equals(CaptchaInitParams.COOKIE)) {
            Cookie cookie = new Cookie(Attributes.CAPTCHA_ID, id);
            response.addCookie(cookie);
        } else {
            request.setAttribute(Attributes.HIDDEN_INPUT, true);
            request.setAttribute(Attributes.CAPTCHA_ID, id);
        }
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(SESSION_MAX_LIFE_TIME);
    }

    @Override
    public BufferedImage get(HttpServletRequest request) {
        String id;

        if (storage.equals(CaptchaInitParams.COOKIE)) {
            id = getCookie(request, Attributes.CAPTCHA_ID).getValue();
        } else {
            id = request.getParameter(Attributes.CAPTCHA_ID);
        }

        return captchas.get(id).getCaptcha().getImage();
    }

    @Override
    public boolean verifyCaptcha(String answer, HttpServletRequest request) {
        String id;

        if (storage.equals(CaptchaInitParams.COOKIE)) {
            id = getCookie(request, Attributes.CAPTCHA_ID).getValue();
        } else {
            id = request.getParameter(Attributes.CAPTCHA_ID);
        }

        String captcha = captchas.get(id).getCaptcha().getAnswer();
        captchas.remove(id);

        return captcha != null && captcha.equals(answer);
    }

    /**
     * Scans captchas and removes invalid ones.
     */
    private void invalidate() {
        captchas.entrySet().removeIf(element -> !element.getValue().isValid());
    }

    private Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}

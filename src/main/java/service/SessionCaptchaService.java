package service;

import util.CaptchaWrapper;
import util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class SessionCaptchaService extends CaptchaService {

    @Override
    public void generate(HttpServletRequest request, HttpServletResponse response) {
        String id = UUID.randomUUID().toString();
        CaptchaWrapper captcha = create();

        request.getSession().setAttribute(Constants.Attributes.CAPTCHA_ID, id);
        request.getSession().setAttribute(id, captcha.getCaptcha().getAnswer());
        request.getSession().setAttribute(Constants.Attributes.CAPTCHA + id, captcha.getCaptcha().getImage());
    }

    @Override
    public BufferedImage get(HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute(Constants.Attributes.CAPTCHA_ID);
        return (BufferedImage) request.getSession().getAttribute(Constants.Attributes.CAPTCHA + id);
    }

    @Override
    public boolean verifyCaptcha(String answer, HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute(Constants.Attributes.CAPTCHA_ID);
        String correctAnswer = (String) request.getSession().getAttribute(id);
        return correctAnswer != null && correctAnswer.equals(answer);
    }
}

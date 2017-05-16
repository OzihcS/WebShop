package util;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;

import java.time.LocalDateTime;

/**
 * Contains {@link Captcha} and date of creating.
 */
public class CaptchaWrapper {

    private LocalDateTime created;
    private Captcha captcha;
    private static final int MAX_TIME_CAPTCHA_LIFE = 60 * 1000;

    public CaptchaWrapper() {
        this.captcha = buildCaptcha();
        this.created = LocalDateTime.now();
    }

    private Captcha buildCaptcha() {
        return new Captcha.Builder(150, 75)
                .addText()
                .addBackground(new GradiatedBackgroundProducer())
                .addNoise()
                .build();
    }

    /**
     * @return {@link Captcha}
     */
    public Captcha getCaptcha() {
        return captcha;
    }

    /**
     * Checks captcha validity.
     *
     * @return true if captcha exists less than {@code MAX_TIME_CAPTCHA_LIFE}, otherwise false.
     */
    public boolean isValid() {
        return (LocalDateTime.now().getSecond() - created.getSecond()) < MAX_TIME_CAPTCHA_LIFE;
    }
}

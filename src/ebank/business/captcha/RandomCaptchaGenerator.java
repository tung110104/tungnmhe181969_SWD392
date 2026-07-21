package ebank.business.captcha;

import java.util.Random;

/**
 * Generates a random alphanumeric captcha of a fixed length
 * for each login attempt.
 */
public class RandomCaptchaGenerator implements CaptchaGenerator {

    private static final String CHAR_POOL =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final int CAPTCHA_LENGTH = 6;

    private final Random random = new Random();

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder(CAPTCHA_LENGTH);
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }
}

package ebank.business.captcha;

/**
 * Abstraction for captcha generation so the login flow does not
 * depend on how the captcha is actually produced (Dependency Inversion).
 */
public interface CaptchaGenerator {

    /**
     * @return a newly generated captcha string
     */
    String generate();
}

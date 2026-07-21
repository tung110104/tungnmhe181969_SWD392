package ebank.business;

import ebank.business.captcha.CaptchaGenerator;
import ebank.business.captcha.RandomCaptchaGenerator;
import ebank.business.validation.Validator;
import ebank.business.validation.ValidatorFactory;
import ebank.business.validation.ValidatorType;
import ebank.data.LanguageManager;

/**
 * Facade of the login module. Exposes the five functions required by
 * the assignment (setLocate, checkAccountNumber, checkPassword,
 * generateCaptcha, checkCaptcha) and delegates the real work to the
 * language manager, the validators and the captcha generator.
 */
public class Ebank {

    private final LanguageManager languageManager;
    private final CaptchaGenerator captchaGenerator;

    public Ebank() {
        this(new RandomCaptchaGenerator());
    }

    /** The captcha generator is injected so it can be replaced or mocked. */
    public Ebank(CaptchaGenerator captchaGenerator) {
        this.languageManager = LanguageManager.getInstance();
        this.captchaGenerator = captchaGenerator;
    }

    /** Function 1: convert the interface language. */
    public void setLocate(String locate) {
        languageManager.setLocate(locate);
    }

    /** Function 2: check the account number (10 digits). */
    public String checkAccountNumber(String accountNumber) {
        return check(ValidatorFactory.createValidator(ValidatorType.ACCOUNT_NUMBER),
                accountNumber);
    }

    /** Function 3: check the password (8-20 alphanumeric characters). */
    public String checkPassword(String password) {
        return check(ValidatorFactory.createValidator(ValidatorType.PASSWORD),
                password);
    }

    /** Function 4: generate a random captcha code. */
    public String generateCaptcha() {
        return captchaGenerator.generate();
    }

    /** Function 5: check the captcha code. */
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        return check(ValidatorFactory.createValidator(ValidatorType.CAPTCHA, captchaGenerate),
                captchaInput);
    }

    public String getMessage(String key) {
        return languageManager.getMessage(key);
    }

    public String getMessage(String key, Object... args) {
        return languageManager.getMessage(key, args);
    }

    /** Runs one validation strategy and resolves its message in the current language. */
    private String check(Validator validator, String input) {
        String key = validator.validate(input) ? validator.getValidKey()
                                               : validator.getErrorKey();
        return languageManager.getMessage(key);
    }
}

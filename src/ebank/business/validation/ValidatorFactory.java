package ebank.business.validation;

/**
 * Factory Method pattern.
 * Central place that decides which concrete Validator to create,
 * so client code only depends on the Validator interface and never
 * on the concrete classes (Dependency Inversion).
 */
public class ValidatorFactory {

    private ValidatorFactory() {
    }

    /**
     * Creates a validator for the given type.
     *
     * @param type the kind of validation needed
     * @param args extra data required by some validators
     *             (CAPTCHA needs the generated captcha string)
     * @return a concrete Validator for the requested type
     */
    public static Validator createValidator(ValidatorType type, String... args) {
        switch (type) {
            case ACCOUNT_NUMBER:
                return new AccountNumberValidator();
            case PASSWORD:
                return new PasswordValidator();
            case CAPTCHA:
                if (args.length == 0) {
                    throw new IllegalArgumentException(
                            "CAPTCHA validator requires the generated captcha string");
                }
                return new CaptchaValidator(args[0]);
            default:
                throw new IllegalArgumentException("Unsupported validator type: " + type);
        }
    }
}

package ebank.business.validation;

/**
 * Concrete strategy: a password must be 8-20 characters long
 * and contain both letters and digits (alphanumeric).
 */
public class PasswordValidator implements Validator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9]{8,20}$";

    @Override
    public boolean validate(String input) {
        return input != null && input.matches(PASSWORD_PATTERN);
    }

    @Override
    public String getValidKey() {
        return "password.valid";
    }

    @Override
    public String getErrorKey() {
        return "password.invalid";
    }
}

package ebank.business.validation;

/**
 * Concrete strategy: an account number must be numeric
 * and contain exactly 10 digits.
 */
public class AccountNumberValidator implements Validator {

    private static final String ACCOUNT_PATTERN = "^\\d{10}$";

    @Override
    public boolean validate(String input) {
        return input != null && input.matches(ACCOUNT_PATTERN);
    }

    @Override
    public String getValidKey() {
        return "account.valid";
    }

    @Override
    public String getErrorKey() {
        return "account.invalid";
    }
}

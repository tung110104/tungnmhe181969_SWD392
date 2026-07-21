package ebank.business.validation;

/**
 * Strategy pattern - common interface for all validation strategies.
 * Each concrete validator encapsulates one validation rule, so new
 * rules can be added without touching existing code (Open/Closed).
 */
public interface Validator {

    /**
     * @param input the value entered by the user
     * @return true if the input satisfies the rule of this validator
     */
    boolean validate(String input);

    /** Message key returned when the input is valid. */
    String getValidKey();

    /** Message key returned when the input is invalid. */
    String getErrorKey();
}

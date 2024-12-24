package gym.Exception;

public class InvalidAgeException extends Exception {
    private static final String DEFAULT_MESSAGE = "Error: gym.customers.Client must be at least 18 years old to register";
    private static final String DEFAULT_MESSAGE_older = "Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)";
    public InvalidAgeException(int age) {
        super(chooseMessage(age));

    }
    private static String chooseMessage(int age) {
        if (age < 18) {
            return DEFAULT_MESSAGE;
        }
        else {
            return DEFAULT_MESSAGE_older;
        }
    }
}

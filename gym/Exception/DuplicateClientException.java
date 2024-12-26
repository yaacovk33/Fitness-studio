package gym.Exception;

public class DuplicateClientException extends Exception {
    private static final String DEFAULT_MESSAGE = "Error: The client is already registered";
    private static final String DEFAULT_MESSAGE_lesson = "Error: The client is already registered";
    public DuplicateClientException() {
        super(DEFAULT_MESSAGE);
    }

    //public static String getMsg() {
    //    return DEFAULT_MESSAGE;
   // }
}

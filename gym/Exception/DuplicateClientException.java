package gym.Exception;

public class DuplicateClientException extends Exception {
    private static final String DEFAULT_MESSAGE = "Error: The client is already registered";
    private static final String DEFAULT_MESSAGE_lesson = "Error: The client is already registered for this lesson";
    public DuplicateClientException(String msg) {
        super(getMsg(msg));
    }

    public static String getMsg(String msg) {
        if("lesson".equals(msg)) {
            return DEFAULT_MESSAGE_lesson;
        }
     return DEFAULT_MESSAGE;
    }
}

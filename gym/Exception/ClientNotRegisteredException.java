package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Error: The client is not registered with the gym and cannot enroll in lessons";
    private static final String DEFAULT_MESSAGE_unregister = "Error: Registration is required before attempting to unregister";

    public ClientNotRegisteredException(String message) {
        super(ChooseClientNotRegistered(message));
    }
    private static String ChooseClientNotRegistered(String message) {
        if("unregister".equals(message)){
            return DEFAULT_MESSAGE_unregister;
        }
        return DEFAULT_MESSAGE;
    }
}

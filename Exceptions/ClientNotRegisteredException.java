package Exceptions;

public class ClientNotRegisteredException extends RuntimeException {
    public ClientNotRegisteredException(String message) {
        super(message);
    }
}

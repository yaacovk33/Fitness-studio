package gym.management.Sessions;

public class SessionType {
    /** Defining constant SessionType objects for different types of gym sessions
     *
     */
    public static final SessionType Pilates = new SessionType("Pilates");
    public static final SessionType MachinePilates = new SessionType("MachinePilates");
    public static final SessionType ThaiBoxing = new SessionType("ThaiBoxing");
    public static final SessionType Ninja = new SessionType("Ninja");

    private final String name;

    /**
     * Private constructor to prevent creating new SessionType objects directly
     * @param name
     */
    private SessionType(String name) {
        this.name = name;
    }

    /** getter
     *
     * @return The name of the session type
     */
    public String getName() {
        return name;
    }

    /**
     * Overriding the toString() method to return the name of the session type.
     * @return The name of the session type as a string.
     */
    @Override
    public String toString() {
        return name;
    }

}
package gym;
import gym.management.Sessions.Session;
/**
 * The Notifier interface defines methods for notifying clients or sessions
 * in the gym management system.
 */
public interface Notifier {
    /**
     * Sends a message to all clients.
     *
     * @param msg the message to send
     */
    void updateAll(String msg);
    /**
     * Sends a message to a specific session.
     *
     * @param session the session to update
     * @param msg the message to send
     */
    void updateSession(Session session,String msg);
    /**
     * Sends a message to all sessions scheduled on a specific date.
     *
     * @param day the date in "dd-MM-yyyy" format
     * @param msg the message to send
     */
    void updateDate(String day,String msg);
}
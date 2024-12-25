package gym;
import gym.management.Sessions.Session;

public interface Notifier {
    void updateAll(String msg);
    void updateSession(Session session,String msg);
    void updateDate(String day,String msg);
}
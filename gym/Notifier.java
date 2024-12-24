package gym;
import gym.management.Sessions.Session;

public interface Notifier {
    void updateAll(String masage); // הוספת צופה
    void updateSession(Session session,String massage); // הסרת צופה
    void updateDate(String day,String massage); // שליחת הודעה לצופים

}
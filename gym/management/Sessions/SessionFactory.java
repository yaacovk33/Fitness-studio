package gym.management.Sessions;

public class SessionFactory {
    public static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) throws Exception {
        if (type == SessionType.Pilates){
            return new Session(type, date, forum, instructor, 30, 30);
        }
        if (type == SessionType.MachinePilates){
            return new Session(type, date, forum, instructor, 80, 10);
        }
        if (type == SessionType.Ninja){
            return new Session(type, date, forum, instructor, 150, 5);
        }
        if (type == SessionType.ThaiBoxing){
            return new Session(type, date, forum, instructor, 100, 20);
        }
        throw new Exception("Invalid Session Type");
    }
}

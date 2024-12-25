package gym.management.Sessions;

public class SessionFactory {
    public static Session createSession(SessionType2 type, String date, ForumType forum, Instructor instructor) {
        if (!instructor.getSessionType().contains(type)) {
            throw new IllegalArgumentException("Instructor is not qualified to teach this session type.");
        }
        return new Session(type, date, forum, instructor);
    }
}

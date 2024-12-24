package gym.management.Sessions;

public class SessionFactory {
    public static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) {
        // בדיקה האם המדריך מוסמך לסוג השיעור
        if (!instructor.getSessionType().contains(type)) {
            throw new IllegalArgumentException("Instructor is not qualified to teach this session type.");
        }

        // יצירת השיעור
        return new Session(type, date, forum, instructor);

    }
}

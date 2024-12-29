package gym.management.Sessions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionFactory {
    /**
     * Formats a given date string into a LocalDateTime object.
     *
     * @param inputDateTime the input date string in "dd-MM-yyyy HH:mm" format
     * @return the parsed LocalDateTime object
     */
    public static LocalDateTime formatDate(String inputDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(inputDateTime, inputFormatter);
    }

    /**
     * Creates a session based on the specified session type and attributes.
     *
     * @param type the type of session to create
     * @param date the date and time of the session in "dd-MM-yyyy HH:mm" format
     * @param forum the forum type of the session
     * @param instructor the instructor assigned to the session
     * @return the created Session object
     * @throws IllegalArgumentException if the session type is invalid
     */
    public static Session createSession(SessionType type, String date, ForumType forum, Instructor instructor) throws Exception {
        if (type == SessionType.Pilates){
            return new Session(type, formatDate(date) , forum, instructor, 60, 30);
        }
        if (type == SessionType.MachinePilates){
            return new Session(type, formatDate(date), forum, instructor, 80, 10);
        }
        if (type == SessionType.Ninja){
            return new Session(type, formatDate(date), forum, instructor, 150, 5);
        }
        if (type == SessionType.ThaiBoxing){
            return new Session(type, formatDate(date), forum, instructor, 100, 20);
        }
        throw new Exception("Invalid Session Type");
    }
}

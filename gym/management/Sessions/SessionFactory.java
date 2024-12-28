package gym.management.Sessions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionFactory {

    public static LocalDateTime formatDate(String inputDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        //LocalDateTime dateTimeOutput = LocalDateTime.parse(dateTime, outputFormatter);
        return LocalDateTime.parse(inputDateTime, inputFormatter);
    }


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

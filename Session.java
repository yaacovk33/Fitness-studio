import Type.ForumType;
import Type.SessionType;

public class Session {
    private String date;
    private SessionType type;
    private ForumType forumType;
    private Instructor instructor;

    public Session(SessionType type, String date, ForumType forumType, Instructor instructor){
        this.type = type;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;

    }

    public SessionType getType() {
        return type;
    }

    public String getDate(){
        return date;
    }

    public ForumType getForumType() {

        return forumType;
    }
    public Instructor getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return "Session{" +
                "type=" +type +
                ", date & time=' " +date+ '\'' +
                ", forum type= " +forumType+
                ", instructor= " +instructor.getName() +
                '}';

    }
}

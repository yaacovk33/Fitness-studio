package Sessions;

import Type.ForumType;
import Instructor;

public class MachinePilates extends SessionType{
    public MachinePilates(String sessionType, int price, String event, ForumType forumType, Instructor instructor) {
        super(sessionType, price, event, forumType, instructor);
    }
}

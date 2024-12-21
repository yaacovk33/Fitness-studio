import Type.ForumType;
import Type.Gender;
import Type.SessionType;

import java.util.ArrayList;

public class Secretary extends Person {
    private int salary;

    public Secretary(String name, int balance, Gender gender, String birthday, int salary) {
        super(name, balance, gender, birthday);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Client registerClient(Person person) {
    }

    public Instructor hireInstructor(Person person, int i, ArrayList<Object> objects) {
    }

    public void registerClientToLesson(Client client, Session session) {
    }

    public void unregisterClient(Client client) {
    }

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor i2) {
    }

    public void paySalaries() {

    }
}

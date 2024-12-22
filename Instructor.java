import Type.SessionType;

import java.util.ArrayList;
import java.util.HashSet;

public class Instructor extends Person {
    private int salary;
    private ArrayList<SessionType> sessionType;
    static ArrayList<Instructor> instructors;
    //private Person person;
    private int id;
    private int sessionCount;


    public Instructor(Person person, int salary, ArrayList<SessionType> sessionType,int id) {
        super(person);
        this.salary = salary;
        this.sessionType = sessionType;
        this.id = id;
        this.sessionCount= 0;
        instructors= new ArrayList<>();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSessionType(ArrayList<SessionType> sessionType) {
        this.sessionType = sessionType;
    }

    public ArrayList<SessionType> getSessionType() {
        return sessionType;
    }
    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }
    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void addSessionCount(SessionType session) {
        if (sessionType.contains(session)) {
        sessionCount++;
        } else {
            System.out.println("Instructor is not certified to instruct this session type: " + session);
        }

    }
    public void resetSessionCount() {
        sessionCount = 0;
    }
    public int getSessionCount() {
        return sessionCount;
    }


    public String toString() {
        return "Employees data:\n"+
                "ID: " + id +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " +
                " | Balance: " +getBalance() +
                " | Role: " +
                " | Salary per hour: " + getSalary()+
                " | Certified classes: " + getSessionType();

    }
}

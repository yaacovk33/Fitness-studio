package gym.management.Sessions;
import gym.Person;

import java.util.ArrayList;

public class Instructor extends Person {
    private int salaryPerHour;
    private ArrayList<SessionType> sessionType;
    static ArrayList<Instructor> instructors;
    //private gym.Person person;
    private int id;
    private static int sessionCount;


    public Instructor(Person person, int salaryPerHour, ArrayList<SessionType> sessionType) {
        super(person);
        this.salaryPerHour = salaryPerHour;
        this.sessionType = sessionType;
        this.id = id;
        this.sessionCount= 0;
        //instructors= new ArrayList<>();
        instructors.add(this);
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public void setSessionType(ArrayList<SessionType> sessionType) {
        this.sessionType = sessionType;
    }

    public ArrayList<SessionType> getSessionType() {
        return sessionType;
    }

    public static ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public static void setInstructors(ArrayList<Instructor> instructorsList) {
        instructors = instructorsList;
    }

    public int addSessionCount(SessionType session) {
        if (sessionType.contains(session)) {
        sessionCount++;
        } else {
            System.out.println("Instructor is not certified to instruct this session type: " + session);
        }
        return sessionCount;
    }
    public void resetSessionCount() {
        sessionCount = 0;
    }

    public static int getSessionCount() {
        return sessionCount;
    }


    public String toString() {
        return "Employees data:\n"+
                "ID: " + id +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " + calculateAge(getBirthday()) +
                " | Balance: " +getBalance() +
                " | Role: Instructor" +
                " | Salary per hour: " + salaryPerHour +
                " | Certified classes: " + sessionType;

    }
    public boolean isQualified(SessionType2 sessionType) {
        if (sessionType == null) {
            return false;
        }
        return this.sessionType.contains(sessionType);
    }
}

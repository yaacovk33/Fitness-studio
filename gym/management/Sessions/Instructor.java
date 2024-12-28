package gym.management.Sessions;
import gym.Person;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private int salaryPerHour;
    private ArrayList<SessionType> sessionType;


    private static int sessionCount;


    public Instructor(Person person, int salaryPerHour, ArrayList<SessionType> sessionType) {
        super(person);
        this.salaryPerHour = salaryPerHour;
        this.sessionType = sessionType;
        this.sessionCount= 0;

    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

//    public void setSalaryPerHour(int salaryPerHour) {
//        this.salaryPerHour = salaryPerHour;
//    }
//
//    public void setSessionType(ArrayList<SessionType> sessionType) {
//        this.sessionType = sessionType;
//    }

    public ArrayList<SessionType> getSessionType() {
        return sessionType;
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

    public boolean isQualified(SessionType sessionType) {
        if (sessionType == null) {
            return false;
        }
        return this.sessionType.contains(sessionType);
    }
    public String toString() {
        List<String> sessionNames = new ArrayList<>();
        for (SessionType session : sessionType) {
            sessionNames.add(session.toString());  // Assuming SessionType has a meaningful toString()
        }
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " + calculateAge(getBirthday()) +
                " | Balance: " +getBalance() +
                " | Role: Instructor" +
                " | Salary per hour: " + salaryPerHour +
                " | Certified classes: " +  String.join(", ", sessionNames) +
                "\n";

    }
}

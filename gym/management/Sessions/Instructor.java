package gym.management.Sessions;
import gym.Person;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents an Instructor in the gym management system.
 */
public class Instructor extends Person {

    private int salaryPerHour;
    private ArrayList<SessionType> sessionType;
    private  int sessionCount;

    /**
     * Constructs an Instructor with the given person details, salary, and qualified session types.
     *
     * @param person        the person details
     * @param salaryPerHour the instructor's hourly salary
     * @param sessionType   the list of session types the instructor is qualified to teach
     */
    public Instructor(Person person, int salaryPerHour, ArrayList<SessionType> sessionType) {
        super(person);
        this.salaryPerHour = salaryPerHour;
        this.sessionType = sessionType;
        this.sessionCount= 0;

    }
    /**
     * Gets the instructor's hourly salary.
     *
     * @return the salary per hour
     */
    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    /**
     * Gets the list of session types the instructor is qualified to teach.
     *
     * @return the list of qualified session types
     */
    public ArrayList<SessionType> getSessionType() {
        return sessionType;
    }

    /**
     * Adds a session count if the instructor is qualified to teach the session type.
     *
     * @param session the session type to add
     * @return the updated session count
     */

    public int addSessionCount(SessionType session) {
        if (sessionType.contains(session)) {
        sessionCount++;
        } else {
            System.out.println("Instructor is not certified to instruct this session type: " + session);
        }
        return sessionCount;
    }
    /**
     * Resets the session count to zero.
     */
    public void resetSessionCount() {
        sessionCount = 0;
    }
    /**
     * Gets the total number of sessions assigned to the instructor.
     *
     * @return the session count
     */
    public int getSessionCount() {
        return sessionCount;
    }
    /**
     * Checks if the instructor is qualified to teach the given session type.
     *
     * @param sessionType the session type to check
     * @return true if qualified, false otherwise
     */
    public boolean isQualified(SessionType sessionType) {
        if (sessionType == null) {
            return false;
        }
        return this.sessionType.contains(sessionType);
    }
    /**
     * Provides a string representation of the instructor's details.
     *
     * @return a formatted string containing instructor details
     */
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
                " | Salary per Hour: " + salaryPerHour +
                " | Certified Classes: " +  String.join(", ", sessionNames) +
                "\n";
    }
}

package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.Person;
import gym.management.Sessions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Represents a Secretary in the gym management system, responsible for managing clients, sessions, and instructors.
 */

public class Secretary extends Person {
    private int salary;
    private Gym gym;
    private boolean disabled = false;

    /**
     * Constructor for creating a Secretary instance.
     * @param person The person object containing basic details.
     * @param salary The salary of the secretary.
     * @param gym The gym that the secretary works for.
     */

    public Secretary(Person person, int salary, Gym gym) {
        super(person);
        this.salary = salary;
        this.gym = gym;
    }
    /**
     * Gets the salary of the secretary.
     * @return The salary.
     */

    public int getSalary() {
        return salary;
    }
    /**
     * Sets the salary of the secretary.
     * @param salary The new salary.
     */

    public void setSalary(int salary) {
        this.salary = salary;
    }
    /**
     * Disables the secretary, preventing further actions.
     */

    public void disable() {
        this.disabled = true;
    }
    /**
     * Checks if the secretary is disabled.
     * @return True if disabled, false otherwise.
     */

    public boolean isDisabled() {
        return disabled;
    }
    /**
     * Registers a new client to the gym.
     * @param person The person object containing client details.
     * @return The registered client.
     * @throws InvalidAgeException If the client's age is below the minimum required.
     * @throws DuplicateClientException If the client is already registered.
     */


    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        int age = calculateAge(person.getBirthday());
        if (age < 18) {
            throw new InvalidAgeException();
        }
        if (Gym.containsClient(person)) {
            throw new DuplicateClientException("");
        }

        Client client = new Client(person);
        gym.addClients(client);
        gym.addActions("Registered new client: " + client.getName());
        return client;
    }
    /**
     * Unregisters a client from the gym.
     * @param client The client to be unregistered.
     * @throws ClientNotRegisteredException If the client is not registered.
     */

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("unregister");
        } else {
            gym.getClients().remove(client);
            gym.addActions("Unregistered client: " + client.getName());
        }
    }
    /**
     * Hires a new instructor.
     * @param person The person object containing instructor details.
     * @param salary The salary of the instructor.
     * @param sessionType The session types the instructor is qualified for.
     * @return The hired instructor.
     * @throws InstructorNotQualifiedException If the instructor lacks qualifications.
     */

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionType) throws InstructorNotQualifiedException {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new InstructorNotQualifiedException();
        }
        Instructor instructor = new Instructor(person, salary, sessionType);
        gym.getInstructors().add(instructor);
        gym.addActions("Hired new instructor: " + instructor.getName() + " with salary per hour: " + instructor.getSalaryPerHour());
        return instructor;
    }
    /**
     * Adds a new session to the gym.
     * @param sessionType The type of session.
     * @param date The date of the session.
     * @param forumType The forum type of the session.
     * @param instructor The instructor conducting the session.
     * @return The newly created session.
     * @throws Exception If an error occurs during session creation.
     */

    public Session addSession(gym.management.Sessions.SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws Exception {
        if (!instructor.isQualified(sessionType)) {
            throw new InstructorNotQualifiedException();
        }

        Session newSession = SessionFactory.createSession(sessionType, date, forumType, instructor);
        gym.getSessions().add(newSession);
        instructor.addSessionCount(newSession.getType());
        gym.addActions("Created new session: " + sessionType + " on " + newSession.getDate() + " with instructor: " + instructor.getName());
        return newSession;

    }
    /**
     * Registers a client to a specific session.
     * @param client The client to be registered.
     * @param session The session to register the client for.
     * @throws DuplicateClientException If the client is already registered for the session.
     * @throws ClientNotRegisteredException If the client is not registered in the gym.
     */

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        boolean canRegistered = true;

        if (client == null) {
            throw new NullPointerException("no client");
        }
        if (session == null) {
            gym.addActions("Failed registration: Session is not in the future");
            canRegistered = false;
        }
        if (session.getDate().isBefore(LocalDateTime.now())) {
            gym.addActions("Failed registration: Session is not in the future");
            canRegistered = false;
        }
        if (this.isDisabled()) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("not in client list");
        }
        int age = calculateAge(client.getBirthday());
        if (session.getForumType() == ForumType.Seniors && age < 65) {
            gym.addActions("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            canRegistered = false;
        }
        if ((session.getForumType() != ForumType.All && session.getForumType() != ForumType.Seniors)) {
            if (client.getGender() != session.getGender()) {
                gym.addActions("Failed registration: Client's gender doesn't match the session's gender requirements");
                canRegistered = false;
            }
        }
        if (session.getParticipants().contains(client)) {
            throw new DuplicateClientException("lesson");
        }

        if (client.getBalance() < session.getPrice()) {
            gym.addActions("Failed registration: Client doesn't have enough balance");
            canRegistered = false;
        }
        if (session.getCurrentParticipants() >= session.getMaxParticipants()) {
            gym.addActions("Failed registration: No available spots for session");
            canRegistered = false;
        }
        if (!canRegistered) {
            return;
        }

        session.addParticipant(client);
        gym.addBalance(session.getPrice());
        client.subBalance(session.getPrice());
        gym.addActions("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getPrice());

    }
    /**
     * Pays salaries to all employees.
     */

    public void paySalaries() {
        for (Instructor instructor : gym.getInstructors()) {
            int sess = instructor.getSessionCount();
            int salaryFinal = instructor.getSalaryPerHour() * sess;
            gym.subBalance(salaryFinal);
            instructor.addBalance(salaryFinal);
            instructor.resetSessionCount();
        }
        if (gym.getGymSecretary() != null) {
            gym.subBalance(salary);
            gym.getGymSecretary().addBalance(salary);

        }
        gym.addActions("Salaries have been paid to all employees");

    }
    /**
     * Prints all actions taken by the gym.
     */
    public void printActions() {
        for (String action : gym.actions) {
            System.out.println(action);
        }
    }
    /**
     * Sends a notification message to all participants of a session.
     * @param session The session to notify
     * @param s The message to send
     */
    public void notify(Session session, String s) {
        gym.updateSession(session, s);
        gym.addActions("A message was sent to everyone registered for session " + session.getType() + " on " + session.getDate() + " : " + s);
    }
    // Overloaded notify methods to handle different formats of notifications
    public static String formatDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(input, inputFormatter);
        return date.format(outputFormatter);
    }
    /**
     * Sends a notification message to all participants of sessions in a specific day.
     * @param date The date to notify
     * @param s The message to send
     */
    public void notify(String date, String s) {
        gym.updateDate(date, s);
        gym.addActions("A message was sent to everyone registered for a session on " + formatDate(date) + " : " + s);
    }
    /**
     * Sends a notification message to all the clients.
     * @param s The message to send
     */
    public void notify(String s) {
        gym.updateAll(s);
        gym.addActions("A message was sent to all gym clients: " + s);
    }
    /**
     * Returns a string representation of the secretary's details.
     * @return The secretary's details as a string
     */
    public String toString() {
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " + calculateAge(getBirthday()) +
                " | Balance: " + getBalance() +
                " | Role: Secretary" +
                " | Salary per Month: " + salary;
    }
}

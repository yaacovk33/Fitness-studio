package gym.management;

import gym.Notifier;
import gym.Person;
import gym.customers.*;
import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Singleton class representing a gym.
 * Handles management of clients, instructors, sessions, and gym-related actions.
 */

public class Gym implements Notifier {
    private String name;
    private static Gym instance;
    private Secretary gymSecretary;
    private Balance balance;
    private static List<Client> clients = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    protected List<String> actions = new ArrayList<>();
    /**
     * Private constructor to enforce Singleton pattern.
     */

    private Gym() {
        this.name = "";
        this.gymSecretary = null;
        this.balance = new Balance(0);

    }
    /**
     * Gets the singleton instance of the Gym class.
     * @return The single Gym instance.
     */

    public static synchronized Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    /**
     * Gets the name of the gym.
     * @param name Gym name.
     * @return The name of the gym.
     */

    public String getName(String name) {
        return this.name;
    }
    /**
     * Sets the name of the gym.
     * @param name New name of the gym.
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets a new secretary for the gym.
     * Disables the current secretary if one exists.
     * @param person The person to become the new secretary.
     * @param salary Salary of the new secretary.
     */

    public void setSecretary(Person person, int salary) {
        if (gymSecretary != null) {
            gymSecretary.disable();

        }
        gymSecretary = new Secretary(person, salary, this);

        addActions("A new secretary has started working at the gym: " + gymSecretary.getName());

    }
    /**
     * Gets the current secretary of the gym.
     * @return The current secretary.
     */

    public Secretary getGymSecretary() {
        return gymSecretary;
    }
    /**
     * Gets the gym's current balance.
     * @return The current balance.
     */

    public int getBalance() {
        return balance.getBalance();
    }
    /**
     * Adds to the gym's balance.
     * @param balance Amount to add.
     */

    public void addBalance(int balance) {
        this.balance.addBalance(balance);
    }
    /**
     * Subtracts from the gym's balance.
     * @param balance Amount to subtract.
     */

    public void subBalance(int balance) {
        this.balance.subBalance(balance);
    }
    /**
     * Gets the list of clients registered in the gym.
     * @return List of clients.
     */

    public List<Client> getClients() {
        return clients;
    }
    /**
     * Sets the list of clients.
     * @param clients New list of clients.
     */

    public void setClients(List<Client> clients) {
        Gym.clients = clients;
    }
    /**
     * Gets the list of instructors working at the gym.
     * @return List of instructors.
     */

    public List<Instructor> getInstructors() {
        return instructors;
    }
    /**
     * Sets the list of instructors.
     * @param instructors New list of instructors.
     */

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }
    /**
     * Gets the list of sessions available at the gym.
     * @return List of sessions.
     */

    public List <Session> getSessions() {
        return sessions;
    }
    /**
     * Sets the list of sessions.
     * @param sessions New list of sessions.
     */

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    /**
     * Adds a new client to the gym.
     * @param c The client to add.
     */

    public void addClients(Client c) {
        clients.add(c);
    }
    /**
     * Logs an action performed in the gym.
     * @param s The action to log.
     */
    public void addActions(String s) {
        actions.add(s);
    }

    /**
     * Updates all clients with a specific message.
     * @param msg The message to send.
     */

    @Override
    public void updateAll(String msg) {
        for (Client c : clients) {
            c.update(msg);
        }
    }
    /**
     * Updates a specific session with a message.
     * @param session The session to update.
     * @param msg The message to send.
     */

    @Override
    public void updateSession(Session session, String msg) {
        for (Session s : sessions) {
            if (s == session) {
                s.update(msg);
                return;
            }
        }
    }
    /**
     * Formats a LocalDateTime object into a string.
     * @param date The date to format.
     * @return The formatted date as a string.
     */

    public String formatDateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
    /**
     * Updates all sessions scheduled on a specific day with a message.
     * @param day The date to match.
     * @param msg The message to send.
     */

    @Override
    public void updateDate(String day, String msg) {
        for (Session s : sessions) {
            if (formatDateToString(s.getDate()).equals(day)) {
                s.update(msg);
                return;
            }
        }
    }
    /**
     * Checks if a person is already registered as a client.
     * @param client The person to check.
     * @return True if the person is already a client, false otherwise.
     */

    public static boolean containsClient(Person client) {
        for (Client c : clients) {
            if (c.getId() == (client.getId())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Generates a summary of the gym, including clients, instructors, sessions, and balance.
     * @return The string representation of the gym.
     */

    public String toString() {
        StringBuilder resultInstructor = new StringBuilder();
        for (Instructor instructor : instructors) {
            resultInstructor.append(instructor.toString());
        }
        StringBuilder resultSession = new StringBuilder();
        for (Session session : sessions) {
            resultSession.append(session.toString());
        }
        StringBuilder resultClients = new StringBuilder();
        for (Client client : clients) {
            String current = client.toString();
            resultClients.append(current);
        }

        return "Gym Name: " + name + "\n" +
                "Gym Secretary: " + Gym.getInstance().gymSecretary.toString() + "\n" +
                "Gym Balance: " + Gym.getInstance().getBalance() +"\n" + "\n" +
                "Clients Data:" +
                resultClients + "\n" + "\n" +
                "Employees Data:" + "\n" +
                resultInstructor +
                gymSecretary.toString()+"\n" + "\n" +
                "Sessions Data:" +
                resultSession;
    }

}
package gym.management;

import gym.Notifier;
import gym.Person;
import gym.customers.*;
import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gym implements Notifier {
    private String name;
    private static Gym instance;
    private Secretary gymSecretary;
    private int balance;
    private List<Client> clients = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    private List<String> actions = new ArrayList<>();

    private Gym() {
        this.name = "";
        this.gymSecretary = null;
        //this.balance = balance;
    }


    public static synchronized Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public String getName(String name) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecretary(Person person, int salary) {
        if (gymSecretary != null) {
            gymSecretary = null;
        }
        gymSecretary = new Secretary(person.getName(), person.getBalance(), person.getGender(), person.getBirthday(), salary, this);
        balance -= salary;
        actions.add("Appointed new gymSecretary: " + gymSecretary.getName());
        System.out.println("New gymSecretary appointed: " + gymSecretary.getName());
    }

    public Secretary getGymSecretary() {
        return gymSecretary;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void permissionsToSecretary() {
    }

    public void getSubscriptions() {
    }

    public void addClients(Client c) {
        clients.add(c);
    }

    public void removeClients() {
    }

    public void getInstructor() {
    }

    public void addActions(String s) {
        actions.add(s);
    }

    public void getActions() {
    }

    public void getSessions() {
    }

    public void addSessions(Session newSession) {
        sessions.add(newSession);
    }

    @Override
    public void updateAll(String msg) {
        for (Client c : clients) {

            c.update(msg);
        }
    }

    @Override
    public void updateSession(Session session, String msg) {
        for (Session s : sessions) {
            if (s == session) {
                s.update(msg);
                return;
            }
        }
    }

    @Override
    public void updateDate(String day, String msg) {
        for (Session s : sessions) {
            if (s.getDate().equals(day)) {
                s.update(msg);
                return;
            }
        }
    }

    public boolean containsClient(Person client) {
        for (Client c : clients) {
            if (c.getId() == (client.getId())) {
                return true;
            }
        }
        return false;
    }

    public void exportActionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write("---Actions history---");
            writer.newLine();

            for (String errors : actions) {
                writer.write(errors);
                writer.newLine();
            }

            writer.write("");
            writer.newLine();
            for (String action : actions) {
                writer.write(action);
                writer.newLine();
            }
            for (Client c : clients) {
                writer.write(c.getNotifications());
                writer.newLine();
            }
            writer.write(this.name);
            writer.newLine();
            for (Client c : clients) {
                writer.write(c.tostring());
                writer.newLine();
            }

            System.out.println("Actions exported successfully to output.txt");
        } catch (IOException e) {
            System.err.println("Error exporting actions to file: " + e.getMessage());
        }

    }

    public String toString() {
        return "---gym.management.Gym Information---\n" +
                "gym.management.Gym Name: " + name + "\n" +
                "gym.management.Gym gym.management.Secretary: " + gymSecretary.toString() + "\n" +
                "gym.management.Gym Balance: " + balance;
    }
}
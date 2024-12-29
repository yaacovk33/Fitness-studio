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

public class Gym implements Notifier {
    private String name;
    private static Gym instance;
    private Secretary gymSecretary;
    private Secretary newGymSecretary;
    private Balance balance;
    private static List<Client> clients = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    protected List<String> actions = new ArrayList<>();

    private Gym() {
        this.name = "";
        this.gymSecretary = null;
        this.balance = new Balance(0);

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
            gymSecretary.disable();
            //gymSecretary = null;
        }
        gymSecretary = new Secretary(person, salary, this);

        //actions.add("A new secretary has started working at the gym: " + gymSecretary.getName());
        addActions("A new secretary has started working at the gym: " + gymSecretary.getName());

    }

    public Secretary getGymSecretary() {
        return gymSecretary;
    }

    public int getBalance() {
        return balance.getBalance();
    }

    public void addBalance(int balance) {
        this.balance.addBalance(balance);
    }
    public void subBalance(int balance) {
        this.balance.subBalance(balance);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        Gym.clients = clients;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List <Session> getSessions() {
        return sessions;
    }
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void addClients(Client c) {
        clients.add(c);
    }
    public void removeClients(Client c) {
        clients.remove(c);
    }
    public void addActions(String s) {
        actions.add(s);
    }
    public void getActions(List<String> actions) {
        this.actions = actions;
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
    public String formatDateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    @Override
    public void updateDate(String day, String msg) {
        for (Session s : sessions) {
            if (formatDateToString(s.getDate()).equals(day)) {
                s.update(msg);
                return;
            }
        }
    }

    public static boolean containsClient(Person client) {
        for (Client c : clients) {
            if (c.getId() == (client.getId())) {
                return true;
            }
        }
        return false;
    }

  /*  public void exportActionsToFile() {
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
                "Gym Secretary:" + Gym.getInstance().gymSecretary.toString() + "\n" +
                "Gym Balance: " + Gym.getInstance().getBalance() +"\n" + "\n" +
                "Clients Data:" +
                resultClients.toString() + "\n" + "\n" +
                "Employees Data:" + "\n" +
                resultInstructor.toString()+
                gymSecretary.toString()+"\n" + "\n" +
                "Sessions Data:" +
                resultSession.toString();
    }

}
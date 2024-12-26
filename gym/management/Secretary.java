package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.Person;
import gym.management.Sessions.*;

import java.util.ArrayList;


public class Secretary extends Person {
    private int salary;
    private Gym gym;
    //private List<String> actions = new ArrayList<>();


    public Secretary(String name, int balance, Gender gender, String birthday, int salary, Gym gym) {
        super(name, balance, gender, birthday);
        this.salary = salary;
        this.gym = gym;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        int age = calculateAge(person.getBirthday());
        if (age < 18) {
            throw new InvalidAgeException();
        }
        if (Gym.containsClient(person)) {
            gym.addActions(DuplicateClientException.getMsg());
            throw new DuplicateClientException();
        }

        Client client = new Client(person);
        gym.addClients(client);
        gym.addActions("Registered new client: " + client.getName());
        //System.out.println("Registered new client: " + client.getName());
        return client;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("unregister");
        } else {
            gym.getClients().remove(client);
            gym.addActions("Unregistered new client: " + client.getName());
            //System.out.println("Unregistered client: " + client.getName());
        }
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionType) throws InstructorNotQualifiedException {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new InstructorNotQualifiedException();
        }
        Instructor instructor = new Instructor(person, salary, sessionType);
        gym.getInstructors().add(instructor);
        gym.addActions("Hired new instructor: " + instructor.getName()+ "with salary per hour: " + instructor.getSalaryPerHour());
        //System.out.println("Hired new instructor: " + instructor.getName() + "with salary per hour: " + instructor.getSalaryPerHour());
        return instructor;
    }

    public Session addSession(gym.management.Sessions.SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws Exception {
        if (!instructor.isQualified(sessionType)) {
            throw new InstructorNotQualifiedException();
        }

        Session newSession = SessionFactory.createSession(sessionType, date, forumType, instructor);
        gym.getSessions().add(newSession);
        gym.addActions("Created new session: " + sessionType + " on " + newSession.getDate() + " with instructor:  " + instructor);
        //System.out.println("Created new session: " + sessionType + " on " + session.getDate() + " with instructor:  " + instructor);
        return newSession;

    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException {
        if (client == null) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if (session == null) {
            gym.addActions("Failed registration: gym.management.Session is not in the future");
        }
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("not in client list");
        }

        int age = calculateAge(client.getBirthday());
        if (session.getForumType() == ForumType.Seniors && age < 65) {
            gym.addActions("Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)");
            //throw new InvalidAgeException();
        }
        //if gender not equal to forum type
        if (session.getForumType() != ForumType.All && session.getForumType() != ForumType.Seniors) {
            try {
                if (client.getGender() != session.getGender()) {
                    gym.addActions("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
                }
            } catch (Exception e) {
                System.out.println("No gender");
            }
        }
        //price not equal balance
        if (client.getBalance() < session.getPrice()) {
            gym.addActions("Failed registration: gym.customers.Client doesn't have enough balance");
        }

        if (session.getCurrentParticipants() >= session.getMaxParticipants()) {
            gym.addActions("Failed registration: No available spots for session");
        }
        if (!session.isRegistered(client)) {
            session.addParticipant(client);
            gym.addBalance(session.getPrice());
            client.subBalance(session.getPrice());
            gym.addActions("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getPrice());
        } else {
            gym.addActions("Failed registration: gym.customers.Client is already registered to this session");
            throw new DuplicateClientException();
        }

    }

    public void paySalaries() {


        gym.addActions("Salaries have been paid to all employees");

    }

    public void printActions() {
        for (String action : gym.actions){
            System.out.println(action);
        }
    }

    public void notify(Session session, String s) {
        gym.updateSession(session, s);
        gym.addActions("A message was sent to everyone registered for session " + session + " on " +session.getDate()+ " : " + s);
        //System.out.println(session.getType() + " - " + s);

    }

    public void notify(String date, String s) {
        gym.updateDate(date, s);
        gym.addActions("A message was sent to everyone registered for a session on " + date + " : ");
        //System.out.println(date + " - " + s);
    }

    public void notify(String s) {
        gym.updateAll(s);
        gym.addActions("A message was sent to all gym clients: " + s);
        //System.out.println(s);
    }
}

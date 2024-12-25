package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.Person;
import gym.management.Sessions.*;

import java.util.ArrayList;
import java.util.List;


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
            throw new InvalidAgeException(age);
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
        gym.addActions("Hired new instructor: " + instructor.getName()+ "with salary per hour: " + instructor.getSalary());
        //System.out.println("Hired new instructor: " + instructor.getName() + "with salary per hour: " + instructor.getSalary());
        return instructor;
    }

    public Session addSession(gym.management.Sessions.SessionType2 sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.isQualified(sessionType)) {
            throw new InstructorNotQualifiedException();
        }

        Session session = new Session(sessionType, date, forumType, instructor);
        gym.getSessions().add(session);
        gym.addActions("Created new session: " + sessionType + " on " + session.getDate() + " with instructor:  " + instructor);
        //System.out.println("Created new session: " + sessionType + " on " + session.getDate() + " with instructor:  " + instructor);
        return session;

        /*    try {
            Session newSession = SessionFactory.createSession(sessionType, date, forumType, instructor);
            gym.addSessions(newSession);

            gym.addActions("Created new session: " + sessionType + " on " + getDate() + " with instructor:  " + instructor);
            return newSession;
        } catch (InstructorNotQualifiedException e) {
            throw new InstructorNotQualifiedException();
        }
    }
     */
    }

    public void registerClientToLesson(Client client, Session session) throws InvalidAgeException, ClientNotRegisteredException, DuplicateClientException, NullPointerException {
        if (client == null) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if (session == null) {
            gym.addActions("Failed registration: gym.management.Session is not in the future");
        }
        int age = calculateAge(client.getBirthday());
        if (age < 65 && session.getForumType() == ForumType.Seniors) {
           gym.addActions("Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)");
            //throw new InvalidAgeException();
        }
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("not in client list");
        }
        //if gender not equal to forum type
        if (session.getForumType() != client.getGender()) {
            gym.addActions("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
        }
        //price not equal balance
        if(Client.getBalance() < SessionType2.getPrice()){
            gym.addActions("Failed registration: gym.customers.Client doesn't have enough balance");
        }

        if (session.getCurrentParticipants() >= session.getMaxParticipants()) {
            gym.addActions("Failed registration: No available spots for session");
        }
        session.addParticipant(client);
        gym.addActions("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + SessionType2.getPrice());
        //System.out.println("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + SessionType2.getPrice());
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
        gym.addActions("A message was sent to everyone registered for session " + session + " on " +session.getDate()+ " : " + s);
        //System.out.println(session.getType() + " - " + s);

    }

    public void notify(String date, String s) {
        gym.addActions("A message was sent to everyone registered for a session on " + date + " : ");
        //System.out.println(date + " - " + s);
    }

    public void notify(String s) {
        gym.addActions("A message was sent to all gym clients: " + s);
        //System.out.println(s);
    }
}

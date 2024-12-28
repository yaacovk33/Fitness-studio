package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.Person;
import gym.management.Sessions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;



public class Secretary extends Person {
    private int salary;
    private Gym gym;
    private boolean disabled = false;


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
    public void disable() {
        this.disabled = true;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void performAction(String action) {
        if (disabled) {
            throw new IllegalStateException("This secretary is no longer active.");
        }
        // Perform the action
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        int age = calculateAge(person.getBirthday());
        if (age < 18) {
            gym.addErrors("Error: gym.customers.Client must be at least 18 years old to register");
            throw new InvalidAgeException();
        }
        if (Gym.containsClient(person)) {
            //gym.addActions();
            gym.addErrors("Error: The client is already registered");
            throw new DuplicateClientException("");
        }

        Client client = new Client(person);
        gym.addClients(client);
        gym.addActions("Registered new client: " + client.getName());
        //System.out.println("Registered new client: " + client.getName());
        return client;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            gym.addErrors("Error: Registration is required before attempting to unregister");
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
        gym.addActions("Hired new instructor: " + instructor.getName() + " with salary per hour: " + instructor.getSalaryPerHour());
        //System.out.println("Hired new instructor: " + instructor.getName() + "with salary per hour: " + instructor.getSalaryPerHour());
        return instructor;
    }



    public Session addSession(gym.management.Sessions.SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws Exception {
        if (!instructor.isQualified(sessionType)) {
            throw new InstructorNotQualifiedException();
        }

        Session newSession = SessionFactory.createSession(sessionType, date, forumType, instructor);
        gym.getSessions().add(newSession);
        gym.addActions("Created new session: " + sessionType + " on " + newSession.getDate() + " with instructor: " + instructor.getName());
        //System.out.println("Created new session: " + sessionType + " on " + session.getDate() + " with instructor:  " + instructor);
        return newSession;

    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        if (client == null) {
            throw new NullPointerException("no client");
        }
        if (session == null) {
            gym.addActions("Failed registration: Session is not in the future");
            return;
        }
        if (session.getDate().isBefore(LocalDateTime.now())){
            gym.addActions("Failed registration: Session is not in the future");
            return;
        }
        if (this.isDisabled() ) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("not in client list");
        }
        int age = calculateAge(client.getBirthday());
        if (session.getForumType() == ForumType.Seniors && age < 65) {
            gym.addActions("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            return;

            //throw new InvalidAgeException();
        }
        //if gender not equal to forum type
        if ((session.getForumType() != ForumType.All && session.getForumType() != ForumType.Seniors))  {
                if (client.getGender() != session.getGender()) {
                    gym.addActions("Failed registration: Client's gender doesn't match the session's gender requirements");
                    return;
                }
        }

        if (session.getParticipants().contains(client)) {
            throw new DuplicateClientException("lesson");
        }

        if (client.getBalance() < session.getPrice()) {
            gym.addActions("Failed registration: Client doesn't have enough balance");
            return;

        }

        if (session.getCurrentParticipants() >= session.getMaxParticipants()) {
            gym.addActions("Failed registration: No available spots for session");
            return;

        }

        session.addParticipant(client);
        gym.addBalance(session.getPrice());
        client.subBalance(session.getPrice());
        gym.addActions("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getPrice());

//        else {
//            gym.addActions("Failed registration: gym.customers.Client is already registered to this session");
//            throw new DuplicateClientException();
//        }

    }

    public void paySalaries() {

        for (Instructor instructor : gym.getInstructors()) {
            gym.subBalance(instructor.getSalaryPerHour() * instructor.getSessionCount());
            instructor.addBalance(instructor.getSalaryPerHour() * instructor.getSessionCount());
            //gym.addActions("Salaries have been paid to instructor: " + instructor.getName());
        }
        if (gym.getGymSecretary() != null) {
            gym.subBalance(salary);
            gym.addBalance(salary);
            //gym.addActions("Salaries have been paid to all employees");
        }
        gym.addActions("Salaries have been paid to all employees");
    }



    public void printActions() {
        for (String action : gym.actions) {
            System.out.println(action);
        }
    }

    public void notify(Session session, String s) {
        gym.updateSession(session, s);
        gym.addActions("A message was sent to everyone registered for session " + session.getType() + " on " + session.getDate() + " : " + s);

    }

    public void notify(String date, String s) {
        gym.updateDate(date, s);
        gym.addActions("A message was sent to everyone registered for a session on " + date + " : " + s);
        //System.out.println(date + " - " + s);
    }

    public void notify(String s) {
        gym.updateAll(s);
        gym.addActions("A message was sent to all gym clients: " + s);
        //System.out.println(s);
    }
}

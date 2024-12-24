package gym.management;

import Type.ForumType;
import gym.customers.Gender;
import Type.SessionType;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;

import java.util.ArrayList;

public class Secretary extends Person {
    private int salary;


    public Secretary(String name, int balance, Gender gender, String birthday, int salary) {
        super(name, balance, gender, birthday);
        this.salary = salary;
    }
    public void changeSecretary(String name, int balance, Gender gender, String birthday, int salary){
        this.setName(name);
        this.setBalance(balance);
        this.setGender(gender);
        this.setBirthday(birthday);
        this.setSalary(salary);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        int age = calculateAge(person.getBirthday());
        if (age < 18 ) {
            throw new InvalidAgeException(age);
        }

        // Check for duplicate client by comparing name or other unique identifiers
        for (Client existingClient : Client.clients) {
            if (existingClient.getId() == person.getId()) {
                throw new DuplicateClientException();
            }
        }
        Client client = new Client(person);
        Client.clients.add(client);
        System.out.println("Registered new client: " + client.getName());
        return client;
    }
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!Client.clients.contains(client)) {
            throw new ClientNotRegisteredException("unregister");
        } else {
            Client.clients.remove(client);
            System.out.println("Unregistered client: " + client.getName());
        }
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionType) throws InstructorNotQualifiedException {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new InstructorNotQualifiedException();
        }
        Instructor instructor = new Instructor(person, salary, sessionType, getId());
        Instructor.instructors.add(instructor);
        System.out.println("Hired new instructor: " + instructor.getName() + "with salary per hour: " + instructor.getSalary());
        return instructor;
    }
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.isQualified(sessionType)){
            throw new InstructorNotQualifiedException();
        } else {
            Session session = new Session( sessionType, date, forumType, instructor);

            System.out.println("Created new session: " + sessionType + " on " + getDate() + " with instructor:  " + instructor);
        }
    }

    public void registerClientToLesson(Client client, Session session) throws InvalidAgeException, ClientNotRegisteredException, DuplicateClientException, NullPointerException {
        if (client == null) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        int age = calculateAge(client.getBirthday());
        if (age < 65 && session.getForumType() == ForumType.Seniors) {
            throw new InvalidAgeException(age);
        }
        if (!Client.clients.contains(client)){
            throw new ClientNotRegisteredException("not in client list");
        }

        if (session.getCurrentParticipants() >= session.getMaxParticipants()) {
            System.out.println("Failed registration: No available spots for session");
        }
        session.addParticipant(client);
        System.out.println("gym.customers.Client " + client.getName() + " has been successfully registered to the session: " + session.getType());
        System.out.println("Registered client: " + client.getName() + " to session: " + session.getType() +" on " + session.getDate() + " for price: " + getPrice());
    }


    public void paySalaries() {

    }

    public void printActions() {
    }

    public void notify(SessionType session, String s) {
    }

    public void notify(String s, SessionType session) {
    }
}

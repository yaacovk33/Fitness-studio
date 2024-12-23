import Type.ForumType;
import Type.Gender;
import Type.SessionType;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import Sessions.Session;

import java.util.ArrayList;

public class Secretary extends Person {

    private int salary;
    private Person person;
    private Secretary instance;


    private Secretary() {
        super(new Person("no name",0, Gender.Female,"0"));
        //this.salary = salary;
    }

    private Secretary(Person person,int salary) {
        super(person);
        this.salary = salary;
        this.person = person;
    }



    public Secretary getInstance() {
        if (instance == null) {
            instance = new Secretary();
        }
        return instance;
    }

//    public Secretary getInstance(Person person) {
//        if (instance == null) {
//            instance = new Secretary(person);
//        }
//        return instance;
//    }

    public void setPerson(Person person) {
        this.person = person;
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
        return client;
    }


    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionType) throws InstructorNotQualifiedException {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new InstructorNotQualifiedException();
        }
        Instructor instructor = new Instructor(person, salary, sessionType, getId());
        Instructor.instructors.add(instructor);
        return instructor;
    }

    public void registerClientToLesson(Client client, Session session) throws InvalidAgeException, ClientNotRegisteredException, DuplicateClientException, NullPointerException {
        if (client == null) {
            throw new NullPointerException("Client cannot be null.");
        }
        int age = calculateAge(client.getBirthday());
        if (age < 65 && session.getForumType() == ForumType.Seniors) {
            throw new InvalidAgeException(age);
        }
        if (!Client.clients.contains(client)) {
            throw new ClientNotRegisteredException("Client is not registered in the gym.");
        }
        if (session.getCurrentParticipants() >= session.getMaxParticipants()) {
            throw new IllegalStateException("Sessions.Session is already full.");
        }
        session.addParticipant(client);
        System.out.println("Client " + client.getName() + " has been successfully registered to the session: " + session.getType());
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
    }

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor i2) throws InstructorNotQualifiedException {
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


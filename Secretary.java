import Type.ForumType;
import Type.Gender;
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

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary)  {
        this.salary = salary;
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        int age = calculateAge(person.getBirthday());
        if (age < 18 || age > 65) {
            throw new InvalidAgeException("Client must be between 18 and 65 years old to register.");
        }

        // Check for duplicate client by comparing name or other unique identifiers
        for (Client existingClient : Client.clients) {
            if (existingClient.getId() == person.getId()) {
                throw new DuplicateClientException("Client with this name already exists.");
            }
        }
        Client client = new Client(person);
        Client.clients.add(client);
        return client;
    }


    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionType) throws InstructorNotQualifiedException {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new InstructorNotQualifiedException("Instructor must have at least one session type.");
        }
        Instructor instructor = new Instructor(person, salary, sessionType,getId());
        Instructor.instructors.add(instructor);
        return instructor;
    }

    public void registerClientToLesson(Client client, Session session) throws InvalidAgeException, ClientNotRegisteredException,DuplicateClientException, NullPointerException {
        if (client == null) {
            throw new NullPointerException("Client cannot be null.");
        }
        int age = calculateAge(person.getBirthday(client));
        if(age<18)
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
    }

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor i2) throws InstructorNotQualifiedException{
    }

    public void paySalaries() {

    }

    public void printActions() {
    }

    public void notify(SessionType session, String s) {

    }

    public void notify(String s,SessionType session) {
    }
}

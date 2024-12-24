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


    public Secretary(String name, int balance, Gender gender, String birthday, int salary, Gym gym) {
        super(name, balance, gender, birthday);
        this.salary = salary;
        this.gym= gym;
    }
  //  public void changeSecretary(String name, int balance, Gender gender, String birthday, int salary){
  //      this.setName(name);
  //      this.setBalance(balance);
  //      this.setGender(gender);
  //      this.setBirthday(birthday);
  //      this.setSalary(salary);
  //  }

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
        if(gym.containsClient(person)){
            gym.addActions(DuplicateClientException.getMsg());
            throw new DuplicateClientException();
        }

        Client client = new Client(person);
        gym.addClients(client);
        System.out.println("Registered new client: " + client.getName());
        return client;
    }
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("unregister");
        } else {
            gym.getClients().remove(client);
            System.out.println("Unregistered client: " + client.getName());
        }
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionType) throws InstructorNotQualifiedException {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new InstructorNotQualifiedException();
        }
        Instructor instructor = new Instructor(person, salary, sessionType, getId());
        gym.getInstructors().add(instructor);
        System.out.println("Hired new instructor: " + instructor.getName() + "with salary per hour: " + instructor.getSalary());
        return instructor;
    }
    public Session addSession(gym.management.Sessions.SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
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

        if (!instructor.isQualified(sessionType)){
            throw new InstructorNotQualifiedException();
        } else {
            Session session = new Session( sessionType, date, forumType, instructor);

            System.out.println("Created new session: " + sessionType + " on " + session.getDate() + " with instructor:  " + instructor);
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
        if (!gym.getClients().contains(client)){
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

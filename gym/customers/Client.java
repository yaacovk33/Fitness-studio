package gym.customers;

import gym.Person;

import java.util.ArrayList;
/**
 * Represents a Client in the gym customers system.
 */
public class Client extends Person {
    private ArrayList<String> notifs;
    /**
     *  Constructor that takes a Person object and initializes the notifications list
     *
     * @param person the person details
     */

    public Client(Person person) {
        super(person);
        this.notifs = new ArrayList<>();
    }

    /**
     * Returns the notifications as a string, formatted in a list
     * @return
     */
    public String getNotifications() {
        return "[" + String.join(", ", notifs) + "]";

    }
    /**
     * Adds a new notification message to the list
     * @return msg
     */
    public  void update(String msg){
        notifs.add(msg);
    }
    /**
     * Provides a string representation of the Client's details.
     *
     * @return a formatted string containing instructor details
     */
    public String toString() {
        return "\n" +"ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " + calculateAge(getBirthday()) +
                " | Balance: " + getBalance();
    }
}

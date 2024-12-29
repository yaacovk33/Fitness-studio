package gym.customers;

import gym.Person;

import java.util.ArrayList;

public class Client extends Person {
    private ArrayList<String> notifs;


    public Client(Person person) {
        super(person);
        this.notifs = new ArrayList<>();
    }


    public String getNotifications() {
        return "[" + String.join(", ", notifs) + "]";

    }
    public  void update(String msg){
        notifs.add(msg);

    }
    public String toString() {

        return "\n" +"ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " + calculateAge(getBirthday()) +
                " | Balance: " + getBalance();
    }
}

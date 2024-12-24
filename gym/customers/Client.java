package gym.customers;

import gym.Person;

import java.util.ArrayList;

public class Client extends Person {
   // static ArrayList<Client> clients;
    private ArrayList<String> notifs;


    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        //clients = new ArrayList<>();
        //this.notifs = new ArrayList<>();
    }


    public String getNotifications() {
        return "";

    }
    public  void update(String msg){
        notifs.add(msg);

    }
    public String tostring() {
        return "Clients Data:\n" +
                "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthday() +
                " | Age: " +
                " | Balance: " + getBalance();
    }
}

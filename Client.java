import Type.Gender;

import java.util.ArrayList;

public class Client extends Person {
    static ArrayList<Client> clients;
    private ArrayList<String> notifs;


    public Client(Person person) {
        super(person);
        clients = new ArrayList<>();
        this.notifs = new ArrayList<>();
    }


    public String getNotifications() {
        return String.join("\n", notifs);

    }
    public update(String msg){
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

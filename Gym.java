public class Gym {
    private String name;
    private static Gym instance;
    private Secretary secretary;
    private int balance;
    //private List<Action> actions;

    private Gym() {
        this.name = "";
        this.secretary = null;
        //this.balance = balance;
    }


    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    public String getName(String name) {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSecretary(Person person, int salary) {
        this.secretary.getInstance().setPerson(person);
        this.secretary.getInstance().setSalary(salary);
    }

    public Secretary getSecretary() {
        return secretary.getInstance();
    }
    public int getBalance() {return balance;}

    public void setBalance(int balance) {this.balance = balance;}

    public void permissionsToSecretary() {}

    public void getSubscriptions(){}

    public void addClients(){}

    public void removeClients(){}

    public void getInstructor(){}

    public void addActions(){}

    public void getActions(){}

    public void getSessions(){}

    public void addSessions(){}

   /* public String toString() {
        return "---Gym Information---\n" +
                "Gym Name: " + name + "\n" +
                "Gym Secretary: " + secretary.toString() + "\n" +
                "Gym Balance: " + balance;
    }

    */
}

public class Gym {
    private String name;
    private static Gym instance;
    private Secretary secretary;

    private Gym(){
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
        this.secretary = new Secretary(person.getName(), person.getBalance(), person.getGender(), person.getBirthday(), salary);
    }

    public Secretary getSecretary() {
        return secretary;
    }
}

import Type.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    private String name;
    private int balance;
    private Gender gender;
    private String birthday;
    private static int id = 1110;
    private int age;


    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
        this.id = id++;
    }

    public Person(Person person) {
        this.name = person.name;
        this.balance = person.balance;
        this.gender = person.gender;
        this.birthday = person.birthday;
        this.id = person.id;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //public int getAge(){return age;}
    //public void setAge(int age){this.age = age;}

    int calculateAge(String birthday) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(birthday, formatter);


        LocalDate currentDate = LocalDate.now();


        int age = currentDate.getYear() - birthDate.getYear();


        if (currentDate.getMonthValue() < birthDate.getMonthValue() ||
                (currentDate.getMonthValue() == birthDate.getMonthValue() && currentDate.getDayOfMonth() < birthDate.getDayOfMonth())) {
            age--;
        }
        return age;
    }

    public void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be greater than 18");
        }

    }

}

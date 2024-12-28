package gym;

import gym.customers.Gender;
import gym.management.Balance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    private String name;
    private Balance balance;
    private Gender gender;
    private String birthday;
    private static int index = 1111;
    private int id;
    private int age;


    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.balance = new Balance(balance);
        this.gender = gender;
        this.birthday = birthday;
        this.id = index++;
    }

    public Person(Person person) {
        this.name = person.name;
        this.balance = person.balance;
        this.gender = person.gender;
        this.birthday = person.birthday;
        this.id = person.id;
        this.age = person.age;
    }

    public void setPerson(Person person) {
        this.name = person.name;
        this.balance = person.balance;
        this.gender = person.gender;
        this.birthday = person.birthday;
        this.id = person.id;
        this.age = person.age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance.getBalance();
    }

    //public void setBalance(int balance) {
    //    this.balance = balance;
    //}

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

    public int getAge(){return calculateAge(birthday);}

    public void setAge(int age){this.age = age;}

    public void addBalance(int balance) {
        this.balance.addBalance(balance);
    }
    public void subBalance(int balance) {
        this.balance.subBalance(balance);
    }

    protected int calculateAge(String birthday) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(birthday, formatter);


        LocalDate currentDate = LocalDate.now();


        int age = currentDate.getYear() - birthDate.getYear();


        if (currentDate.getMonthValue() < birthDate.getMonthValue() ||
                currentDate.getMonthValue() == birthDate.getMonthValue() && currentDate.getDayOfMonth() < birthDate.getDayOfMonth()) {
            age--;
        }
        return age;
    }

}

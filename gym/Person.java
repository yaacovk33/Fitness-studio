package gym;

import gym.customers.Gender;
import gym.management.Balance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The Person class represents a person in the gym system.
 * It contains information about the person's name, balance, gender,
 * birthday, and a unique ID. Additionally, it provides methods for
 * managing the person's balance and calculating their age.
 */

public class Person {

    private String name;
    private Balance balance;
    private Gender gender;
    private String birthday;
    private static int index = 1111;
    private int id;
    private int age;

    /**
     * Constructor to initialize a new Person object.
     *
     * @param name     Name of the person
     * @param balance  Initial balance of the person
     * @param gender   Gender of the person
     * @param birthday Birthday of the person in "dd-MM-yyyy" format
     */

    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.balance = new Balance(balance);
        this.gender = gender;
        this.birthday = birthday;
        this.id = index++;
    }
    /**
     * Copy constructor to create a new Person object from an existing one.
     *
     * @param person The Person object to copy
     */

    public Person(Person person) {
        this.name = person.name;
        this.balance = person.balance;
        this.gender = person.gender;
        this.birthday = person.birthday;
        this.id = person.id;
        this.age = person.age;
    }
    /**
     * Updates the current Person object with the details of another Person object.
     *
     * @param person The Person object whose details will be copied
     */

    public void setPerson(Person person) {
        this.name = person.name;
        this.balance = person.balance;
        this.gender = person.gender;
        this.birthday = person.birthday;
        this.id = person.id;
        this.age = person.age;
    }

    /** Getter and setter methods
     *
     * @return
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance.getBalance();
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

    public int getAge(){return calculateAge(birthday);}

    public void setAge(int age){this.age = age;}
    /**
     * Adds the specified amount to the person's balance.
     *
     * @param balance The amount to add
     */

    public void addBalance(int balance) {
        this.balance.addBalance(balance);
    }
    /**
     * Subtracts the specified amount from the person's balance.
     *
     * @param balance The amount to subtract
     */

    public void subBalance(int balance) {
        this.balance.subBalance(balance);
    }
    /**
     * Calculates the age of the person based on their birthday.
     *
     * @param birthday The birthday of the person in "dd-MM-yyyy" format
     * @return The calculated age
     */

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

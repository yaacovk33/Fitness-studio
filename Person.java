import Type.Gender;

public class Person {

    private String name;
    private int balance;
    private Gender gender;
    private String birthday;



    public Person(String name, int balance, Gender gender, String birthday){
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getName(){
        return name;
    }
    //not sure we need set here
    public void setName(){
        this.name = name;
    }
    public int getBalance(){
        return balance;
    }
    public void setBalance(){
        this.balance = balance;
    }
    public Gender getGender(){
        return gender;
    }
    public void setGender(){
        this.gender = gender;
    }
    public String getBirthday(){
        return birthday;
    }
    public void setBirthday(){
        this.birthday = birthday;
    }
}

package gym.management;

import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;

import java.util.List;

public class Balance {

    private int Balance;

    public Balance(int initialBalance ) {
        this.Balance = initialBalance;

    }

    public int getBalance() {
        return Balance;
    }


    public void addBalance(int amount) {
        Balance += amount;
    }
    public void subBalance(int amount) {
        Balance -= amount;
    }
   /*public boolean chargeClientToLesson(Client client, int sessionPrice) {
    if(client.getBalance() < sessionPrice) {
        return false;
    }
    client.setBalance(client.getBalance() - sessionPrice);
    updateBalance(client.getBalance());
    return true;
    }
    */

    public void paidInstructor(List<Instructor>instructors, List<Session> sessions) {
        for (Instructor instructor : instructors) {
            int salaryFinal = 0;
            for (Session session : sessions) {
                if (session.getInstructor().equals(instructor)) {
                    salaryFinal = (Instructor.getSessionCount() * instructor.getSalaryPerHour());
                }
                updateBalance(-salaryFinal);

            }
        }
    }



}

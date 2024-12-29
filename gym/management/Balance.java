package gym.management;

import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;

import java.util.List;

public class Balance {
    private int Balance;
    /**
     * Constructor to initialize the balance with a specific value.
     * @param initialBalance The initial amount to set the balance to.
     */
    public Balance(int initialBalance ) {
        this.Balance = initialBalance;
    }
    /**
     * Getter method to retrieve the current balance of the gym.
     * @return The current balance.
     */
    public int getBalance() {
        return Balance;
    }
    /**
     * Method to add an amount to the current balance.
     * @param amount The amount to be added to the balance.
     */
    public void addBalance(int amount) {
        Balance += amount;
    }
    /**
     * Method to subtract an amount from the current balance.
     * @param amount The amount to be subtracted from the balance.
     */
    public void subBalance(int amount) {
        Balance -= amount;
    }

}

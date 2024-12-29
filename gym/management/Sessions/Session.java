package gym.management.Sessions;


import gym.customers.Client;
import gym.customers.Gender;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
/**
 * Represents a session held at the gym, including details such as type, date, forum type,
 * instructor, price, and participant management.
 */

public class Session {
    private LocalDateTime date;
    private SessionType type;
    private Instructor instructor;
    private ForumType forumType;
    private ArrayList<Client> participants;
    private int currentParticipants;
    private int maxParticipants;
    private int price;
    /**
     * Constructor for a session with basic attributes.
     *
     * @param type       the type of session.
     * @param date       the date and time of the session.
     * @param forumType  the forum type for the session.
     * @param instructor the instructor leading the session.
     */
    public Session(SessionType type, LocalDateTime date, ForumType forumType, Instructor instructor) {
        this.type = type;
        this.date = date;
        this.instructor = instructor;
        this.forumType = forumType;
    }
    /**
     * Constructor for a session with additional attributes like price and maximum participants.
     *
     * @param type           the type of session.
     * @param date           the date and time of the session.
     * @param forumType      the forum type for the session.
     * @param instructor     the instructor leading the session.
     * @param price          the price of the session.
     * @param maxParticipants the maximum number of participants.
     */
    public Session(SessionType type, LocalDateTime date, ForumType forumType, Instructor instructor, int price, int maxParticipants) {
        this.type = type;
        this.date = date;
        this.instructor = instructor;
        this.forumType = forumType;
        this.currentParticipants = 0;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();
        this.price = price;

    }
    /**
     * Determines the gender restrictions for the session based on its forum type.
     *
     * @return the gender required for the session, or null if open to all genders.
     */

    public Gender getGender() {
        if (ForumType.Male == forumType) {
            return Gender.Male;
        }
        if (ForumType.Female == forumType) {
            return Gender.Female;
        }
        return null;
    }
    /** Getters and setters
     *
     * @return
     */
    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public void setForumType(ForumType forumType) {
        this.forumType = forumType;
    }


    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }
    /**
     * Adds a participant to the session if there is space and they are not already registered.
     *
     * @param client the client to add.
     * @return true if the participant was successfully added, false otherwise.
     */
    public boolean addParticipant(Client client) {
        if (participants.contains(client)) {
            return false;
        }
        if (currentParticipants <= maxParticipants) {
            participants.add(client);
            currentParticipants++;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Removes a participant from the session if they are registered.
     *
     * @param client the client to remove.
     * @return true if the participant was successfully removed, false otherwise.
     */
    public boolean removeParticipant(gym.customers.Client client) {
        if (participants.contains(client)) {
            participants.remove(client);
            currentParticipants--;
            return true;
        }
        return false;
    }

    public int getPrice() {
        return price;
    }
    /**
     * Sends an update message to all participants.
     *
     * @param msg the message to send.
     */
    public void update(String msg) {
        for (Client c : participants) {
            c.update(msg);
        }
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }
    /**
     * Formats a LocalDateTime object to a readable string.
     *
     * @param date the date to format.
     * @return the formatted date string.
     */
    public String formatDateAndHourToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);
    }
    /**
     * Provides a string representation of the session.
     *
     * @return a string detailing the session's attributes.
     */
    @Override
    public String toString() {
        return "\n"+ "Session Type: " + getType() +
                " | Date: " + formatDateAndHourToString(date) +
                " | Forum: " + getForumType() +
                " | Instructor: " + (instructor != null ? instructor.getName() : "None") +
                " | Participants: " + currentParticipants + "/" + maxParticipants;
    }
}

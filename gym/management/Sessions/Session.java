package gym.management.Sessions;


import gym.customers.Client;
import gym.customers.Gender;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;


public class Session {
    private LocalDateTime date;
    private SessionType type;
    private Instructor instructor;
    private ForumType forumType;
    private ArrayList<Client> participants;
    private int currentParticipants;
    private int maxParticipants;
    private int price;

    public Session(SessionType type, LocalDateTime date, ForumType forumType, Instructor instructor) {
        this.type = type;
        this.date = date;
        this.instructor = instructor;
        this.forumType = forumType;
        this.currentParticipants = 0;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();

    }

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

    public Gender getGender() {
        if (ForumType.Male == forumType) {
            return Gender.Male;
        }
        if (ForumType.Female == forumType) {
            return Gender.Female;
        }
        return null;
    }

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

    public boolean isRegistered(Client client) {
        return participants.contains(client);
    }

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

    public void update(String msg) {
        for (Client c : participants) {
            c.update(msg);
        }
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }

    public String formatDateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);  // Convert LocalDateTime to string in the desired format
    }

    @Override
    public String toString() {
        return "Session Type: " + getType() +
                " | Date: " + formatDateToString(date) +
                " | Forum: " + getForumType() +
                " | Instructor: " + (instructor != null ? instructor.getName() : "None") +
                " | Participants: " + currentParticipants + "/" + maxParticipants +
                "\n";
    }
}

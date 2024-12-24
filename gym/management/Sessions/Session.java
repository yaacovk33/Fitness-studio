package gym.management.Sessions;

import gym.customers.Client;

import java.util.ArrayList;

public class Session {
    private String date;
    private SessionType type;
    private Instructor instructor;
    private ForumType forumType;
    private ArrayList<Client> participants;
    private int currentParticipants;
    private int maxParticipants;

    public Session(SessionType type, String date,ForumType forumType ,Instructor instructor){
        this.type = type;
        this.date = date;
        this.instructor = instructor;
        this.forumType=forumType;
        this.currentParticipants = 0;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();

    }

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
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
        if (currentParticipants < maxParticipants) {
            participants.add(client);
            currentParticipants++;
        } else {
            System.out.println("Failed registration: No available spots for session");
            return false;
        }
        return true;
    }

    public boolean removeParticipant(gym.customers.Client client) {
        if (participants.contains(client)) {
            participants.remove(client);
            currentParticipants--;
            return true;
        }
        return false;
    }

    public void update(String msg){
        for (Client c:participants){
            c.update(msg);
        }
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "gym.management.Sessions Data:\n" +
                "gym.management.Sessions.Session Type: " + type +
                " | Date: " + date +
                " | Forum: " + forumType +
                " | Instructor: " + (instructor != null ? instructor.getName() : "None") +
                " | Participants: " + currentParticipants + "/" + maxParticipants;
    }
}

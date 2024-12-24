package gym.management;

import Type.ForumType;

import java.util.ArrayList;

public class SessionType {
    private String name;
    private int price;
    private String event;
   //private Instructor instructor;
    private ForumType forumType;
   //private ArrayList<gym.customers.Client> participants;
   //private int currentParticipants;
    private int maxParticipants;
    private ArrayList<SessionType> sessionTypes;


    public SessionType(String name, int price, String event,ForumType forumType, Instructor instructor) {
        this.name = name;
        this.price = price;
        this.event = event;
        this.forumType = forumType;
      //  this.instructor = instructor;
      //  this.participants = new ArrayList<>();
      //  this.currentParticipants = 0;
        this.maxParticipants = 0;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
   // public Instructor getInstructor() {
    //    return instructor;
   //}
    //public void setInstructor(Instructor instructor) {
    //  this.instructor = instructor;
   //}
    public ForumType getForumType() {
        return forumType;
    }
    public void setForumType(ForumType forumType) {
        this.forumType = forumType;
    }
    //public ArrayList<gym.customers.Client> getParticipants() {
    //  return participants;
    //}
    //public void setParticipants(ArrayList<gym.customers.Client> participants) {
    //  this.participants = participants;
    //}
    //public int getCurrentParticipants() {
    //  return currentParticipants;
    //}
    //public void setCurrentParticipants(int currentParticipants) {
    //  this.currentParticipants = currentParticipants;
    /// }
    public int getMaxParticipants() {
        return maxParticipants;
    }
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
   /* public boolean addParticipant(gym.customers.Client client) {
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

    @Override
    public String toString() {
        return "gym.management.gym.management.Sessions Data:\n" +
                "gym.management.Session Type: " + name +
                " | Date: " + event +
                " | Forum: " + forumType +
                " | Instructor: " + (instructor != null ? instructor.getName() : "None") +
                " | Participants: " + currentParticipants + "/" + maxParticipants;
    }

    */
}


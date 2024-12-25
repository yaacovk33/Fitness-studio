package gym.management.Sessions;

public class SessionType2 {
    private String name;
    private int price;
   // private int currentParticipants;
    private int maxParticipants;
    //private ArrayList<gym.management.SessionType> sessionTypes;


    public SessionType2(String name, int price, int maxParticipants) {
        this.name = name;
        this.price = price;
        //this.currentParticipants = 0;
        this.maxParticipants = maxParticipants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }


/*
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

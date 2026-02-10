package VETprojectfull.model;


public class Appointment {
    private int id;
    private String petName;
    private String ownerName;
    private String reason;

    public Appointment(int id, String petName, String ownerName, String reason) {
        this.id = id;
        this.petName = petName;
        this.ownerName = ownerName;
        this.reason = reason;
    }

    public int getId() { return id; }
    public String getPetName() { return petName; }
    public String getOwnerName() { return ownerName; }
    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }

    @Override
    public String toString() {
        return "ID: " + id + " | Питомец: " + petName + " | Владелец: " + ownerName + " | Причина: " + reason;
    }
}
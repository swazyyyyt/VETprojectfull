package VETprojectfull.model;

public abstract class Treatment {
    protected int treatmentId;
    protected Client client;
    protected Pet pet;
    protected Veterinarian veterinarian;
    protected String status;

    public Treatment(int treatmentId, Client client, Pet pet, Veterinarian veterinarian, String status) {
        this.treatmentId = treatmentId;
        this.client = client;
        this.pet = pet;
        this.veterinarian = veterinarian;
        setStatus(status);
    }

    // Getters
    public int getTreatmentId() { return treatmentId; }
    public Client getOwner() { return client; }
    public Pet getPet() { return pet; }
    public Veterinarian getVeterinarian() { return veterinarian; }
    public String getStatus() { return status; }

    // Setter with validation
    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty!");
        }
        this.status = status;
    }

    // Abstract methods
    public abstract void completeTreatment();
    public abstract String getTreatmentType();

    public boolean isCompleted() {
        return status.equalsIgnoreCase("Completed");
    }

    @Override
    public String toString() {
        return "Treatment{id=" + treatmentId + ", owner='" + client.getName() +
                "', pet='" + pet.getName() + "', vet='" + veterinarian.getName() +
                "', status='" + status + "'}";
    }
}

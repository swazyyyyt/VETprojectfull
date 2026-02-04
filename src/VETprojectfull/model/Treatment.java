package VETprojectfull.model;

public abstract class Treatment {
    protected int treatmentId;
    protected Owner owner;
    protected Pet pet;
    protected Veterinarian veterinarian;
    protected String status;

    public Treatment(int treatmentId, Owner owner, Pet pet, Veterinarian veterinarian, String status) {
        setTreatmentId(treatmentId);
        setOwner(owner);
        setPet(pet);
        setVeterinarian(veterinarian);
        setStatus(status);
    }

    // Getters
    public int getTreatmentId() { return treatmentId; }
    public Owner getOwner() { return owner; }
    public Pet getPet() { return pet; }
    public Veterinarian getVeterinarian() { return veterinarian; }
    public String getStatus() { return status; }

    // Setters with validation
    public void setTreatmentId(int treatmentId) {
        if (treatmentId <= 0) {
            throw new IllegalArgumentException("Treatment ID must be positive!");
        }
        this.treatmentId = treatmentId;
    }

    public void setOwner(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null!");
        }
        this.owner = owner;
    }

    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet cannot be null!");
        }
        this.pet = pet;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new IllegalArgumentException("Veterinarian cannot be null!");
        }
        this.veterinarian = veterinarian;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty!");
        }
        this.status = status;
    }

    // Abstract method - children MUST implement
    public abstract void completeTreatment();

    // Abstract method for treatment type
    public abstract String getTreatmentType();

    public boolean isCompleted() {
        return status.equalsIgnoreCase("Completed");
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", owner='" + owner.getName() + '\'' +
                ", pet='" + pet.getName() + '\'' +
                ", veterinarian='" + veterinarian.getName() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
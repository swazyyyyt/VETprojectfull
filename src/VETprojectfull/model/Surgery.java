package VETprojectfull.model;

public class Surgery extends Treatment implements Treatable {
    private int durationHours;

    public Surgery(int treatmentId, Client client, Pet pet, Veterinarian veterinarian,
                   String status, int durationHours) {
        super(treatmentId, client, pet, veterinarian, status);
        setDurationHours(durationHours);
    }

    public int getDurationHours() { return durationHours; }

    public void setDurationHours(int durationHours) {
        if (durationHours <= 0) {
            throw new IllegalArgumentException("Duration must be positive!");
        }
        if (durationHours > 12) {
            throw new IllegalArgumentException("Duration cannot exceed 12 hours!");
        }
        this.durationHours = durationHours;
    }

    public String getDifficulty() {
        if (durationHours <= 2) return "Simple";
        else if (durationHours <= 5) return "Moderate";
        else return "Complex";
    }

    @Override
    public void completeTreatment() {
        this.status = "Completed";
        System.out.println("✅ Surgery completed for " + pet.getName() + " (" + getDifficulty() + ")");
    }

    @Override
    public String getTreatmentType() {
        return "Surgery";
    }

    @Override
    public void performTreatment() {
        System.out.println("🏥 Performing surgery on " + pet.getName());
    }

    @Override
    public double calculateCost() {
        return durationHours * 50000; // 50,000 KZT per hour
    }

    @Override
    public String toString() {
        return super.toString() + " | Duration: " + durationHours + "h | Difficulty: " + getDifficulty();
    }
}

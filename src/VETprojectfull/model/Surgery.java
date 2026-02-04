package VETprojectfull.model;

public class Surgery extends Treatment implements Treatable {
    private int durationHours;

    public Surgery(int treatmentId, Owner owner, Pet pet, Veterinarian veterinarian,
                   String status, int durationHours) {
        super(treatmentId, owner, pet, veterinarian, status);
        setDurationHours(durationHours);
    }

    public int getDurationHours() {
        return durationHours;
    }

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
        System.out.println("Surgery (" + getDifficulty() + ") completed for " + pet.getName() +
                " by Dr. " + veterinarian.getName() + " - Duration: " + durationHours + " hours");
    }

    @Override
    public String getTreatmentType() {
        return "Surgery";
    }

    @Override
    public void performTreatment() {
        System.out.println("Performing surgery on " + pet.getName());
    }

    @Override
    public double calculateCost() {
        return durationHours * 50000; // 50,000 KZT per hour
    }

    public boolean isMajorSurgery() {
        return durationHours > 4;
    }

    @Override
    public String toString() {
        return super.toString() + " | Duration: " + durationHours +
                " hours | Difficulty: " + getDifficulty();
    }
}
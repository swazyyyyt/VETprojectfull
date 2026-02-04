package VETprojectfull.model;

public class Vaccination extends Treatment implements Treatable {
    private String vaccineName;

    public Vaccination(int treatmentId, Owner owner, Pet pet, Veterinarian veterinarian,
                       String status, String vaccineName) {
        super(treatmentId, owner, pet, veterinarian, status);
        setVaccineName(vaccineName);
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        if (vaccineName == null || vaccineName.trim().isEmpty()) {
            throw new IllegalArgumentException("Vaccine name cannot be empty!");
        }
        this.vaccineName = vaccineName;
    }

    @Override
    public void completeTreatment() {
        this.status = "Completed";
        System.out.println("Vaccination '" + vaccineName + "' completed for " + pet.getName() +
                " by Dr. " + veterinarian.getName());
    }

    @Override
    public String getTreatmentType() {
        return "Vaccination";
    }

    @Override
    public void performTreatment() {
        System.out.println("Administering vaccine: " + vaccineName + " to " + pet.getName());
    }

    @Override
    public double calculateCost() {
        return 15000; // 15,000 KZT flat rate
    }

    public boolean isAnnualVaccine() {
        return vaccineName.toLowerCase().contains("annual") ||
                vaccineName.toLowerCase().contains("rabies");
    }

    @Override
    public String toString() {
        return super.toString() + " | Vaccine: " + vaccineName;
    }
}
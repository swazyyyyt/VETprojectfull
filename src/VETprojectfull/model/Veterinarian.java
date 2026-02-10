package VETprojectfull.model;

public class Veterinarian {
    private int vetId;
    private String name;
    private String specialization;
    private int experience;
    private String phone;

    public Veterinarian(int vetId, String name, String specialization, int experience, String phone) {
        this.vetId = vetId;
        setName(name);
        setSpecialization(specialization);
        setExperience(experience);
        setPhone(phone);
    }

    // Getters
    public int getVetId() { return vetId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }
    public String getPhone() { return phone; }

    // Setters with validation
    public void setVetId(int vetId) {
        if (vetId < 0) {
            throw new IllegalArgumentException("Vet ID cannot be negative!");
        }
        this.vetId = vetId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty!");
        }
        this.specialization = specialization;
    }

    public void setExperience(int experience) {
        if (experience < 0) {
            throw new IllegalArgumentException("Experience cannot be negative!");
        }
        if (experience > 50) {
            throw new IllegalArgumentException("Experience cannot exceed 50 years!");
        }
        this.experience = experience;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be empty!");
        }
        this.phone = phone;
    }

    // Methods
    public boolean isExperienced() {
        return experience >= 5;
    }

    public boolean canTreat(String species) {
        return specialization.equalsIgnoreCase(species);
    }

    @Override
    public String toString() {
        return "Veterinarian{id=" + vetId + ", name='" + name + "', spec='" + specialization +
                "', exp=" + experience + ", phone='" + phone + "'}";
    }
}


package VETprojectfull.model;

public class Veterinarian {
    private int vetId;
    private String name;
    private String specialization;
    private int experience;
    private String phone;

    // Константы для лимитов
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_SPECIALIZATION_LENGTH = 50;
    private static final int MIN_EXPERIENCE = 0;
    private static final int MAX_EXPERIENCE = 50;
    private static final int MIN_PHONE_LENGTH = 10;
    private static final int MAX_PHONE_LENGTH = 15;

    // Constructor
    public Veterinarian(int vetId, String name, String specialization, int experience, String phone) {
        setVetId(vetId);
        setName(name);
        setSpecialization(specialization);
        setExperience(experience);
        setPhone(phone);
    }

    // Getters
    public int getVetId() {
        return vetId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    public String getPhone() {
        return phone;
    }

    // Setters с расширенной валидацией
    public void setVetId(int vetId) {
        if (vetId > 0) {
            this.vetId = vetId;
        } else {
            throw new IllegalArgumentException("Vet ID must be positive!");
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name cannot exceed " + MAX_NAME_LENGTH + " characters!");
        }
        this.name = name.trim();
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty!");
        }
        if (specialization.length() > MAX_SPECIALIZATION_LENGTH) {
            throw new IllegalArgumentException("Specialization cannot exceed " + MAX_SPECIALIZATION_LENGTH + " characters!");
        }
        this.specialization = specialization.trim();
    }

    public void setExperience(int experience) {
        if (experience < MIN_EXPERIENCE) {
            throw new IllegalArgumentException("Experience cannot be negative!");
        }
        if (experience > MAX_EXPERIENCE) {
            throw new IllegalArgumentException("Experience cannot exceed " + MAX_EXPERIENCE + " years!");
        }
        this.experience = experience;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be empty!");
        }
        this.phone = phone.trim();
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
        return "Veterinarian{" +
                "vetId=" + vetId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + experience +
                ", phone='" + phone + '\'' +
                ", experienced=" + (isExperienced() ? "Yes" : "No") +
                '}';
    }
}
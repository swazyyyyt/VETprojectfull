package VETprojectfull.model;

import java.util.ArrayList;

public class Owner {
    private int ownerId;
    private String name;
    private String phone;
    private ArrayList<Pet> pets;

    // Constructor
    public Owner(int ownerId, String name, String phone) {
        this.ownerId = ownerId;
        this.name = name;
        this.phone = phone;
        this.pets = new ArrayList<>();
    }

    // Getters
    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    // Setters
    public void setOwnerId(int ownerId) {
        if (ownerId <= 0) {
            throw new IllegalArgumentException("Owner ID must be positive!");
        }
        this.ownerId = ownerId;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        }
    }

    // Methods
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public int getNumberOfPets() {
        return pets.size();
    }

    public boolean isFrequentClient() {
        return pets.size() >= 3;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfPets=" + pets.size() +
                '}';
    }
}
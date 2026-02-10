package VETprojectfull.model;

import java.util.ArrayList;

public class Client {
    private int ownerId;
    private String name;
    private String phone;

    public Client(int ownerId, String name, String phone) {
        this.ownerId = ownerId;
        setName(name);
        setPhone(phone);
    }

    // Getters
    public int getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    // Setters with validation
    public void setOwnerId(int ownerId) {
        if (ownerId < 0) {
            throw new IllegalArgumentException("Owner ID cannot be negative!");
        }
        this.ownerId = ownerId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be empty!");
        }
        this.phone = phone;
    }

    // Methods


    @Override
    public String toString() {
        return "Client: {id=" + ownerId + ", name= " + name + ", phone=" + phone+ "}";
    }

}
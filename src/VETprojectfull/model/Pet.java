
package VETprojectfull.model;

public class Pet {
    private int petId;
    private String name;
    private String species;
    private int age;
    private Client client;

    public Pet(String name, String species, int age, Client client) {
        setName(name);
        setSpecies(species);
        setAge(age);
        this.client = client;
    }

    // Getters
    public int getPetId() { return petId; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public Client getOwner() { return client; }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name;
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be empty!");
        }
        this.species = species;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative!");
        }
        if (age > 30) {
            throw new IllegalArgumentException("Age cannot exceed 30 years!");
        }
        this.age = age;
    }

    public void setOwner(Client client) {
        this.client = client;
    }

    // Methods
    public boolean isYoung() {
        return age < 2;
    }

    public String getLifeStage() {
        if (age < 2) return "Baby";
        else if (age < 7) return "Adult";
        else return "Senior";
    }

    @Override
    public String toString() {
        return "Pet{name='" + name + "', species='" + species + "', age=" + age +
                ", owner='" + client.getName() + "', stage='" + getLifeStage() + "'}";
    }
}
package VETprojectfull.model;

public class Pet {
    private static int nextId = 1;
    private int petId;
    private String name;
    private String species;
    private int age;
    private Owner owner;

    // Constructor
    public Pet(String name, String species, int age, Owner owner) {
        this.petId = nextId++;
        this.name = name;
        this.species = species;
        this.age = age;
        this.owner = owner;
        owner.addPet(this); // Add this pet to owner's list
    }

    // Getters
    public int getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public Owner getOwner() {
        return owner;
    }

    // Setters
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setSpecies(String species) {
        if (species != null && !species.trim().isEmpty()) {
            this.species = species;
        }
    }

    // Константа для лимита
    private static final int MAX_AGE = 30;

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative!");
        }
        if (age > MAX_AGE) {
            throw new IllegalArgumentException("Age cannot exceed " + MAX_AGE + " years!");
        }
        this.age = age;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // Methods
    public boolean isYoung() {
        return age < 2;
    }

    public String getLifeStage() {
        if (age < 2) {
            return "Baby";
        } else if (age < 7) {
            return "Adult";
        } else {
            return "Senior";
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", owner='" + owner.getName() + '\'' +
                ", lifeStage='" + getLifeStage() + '\'' +
                '}';
    }
}
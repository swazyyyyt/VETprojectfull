package VETprojectfull.menu;

import VETprojectfull.model.*;
import VETprojectfull.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClinicMenu implements Menu {
    private ArrayList<Owner> owners;
    private ArrayList<Veterinarian> veterinarians;
    private ArrayList<Treatment> treatments;
    private Scanner scanner;

    public ClinicMenu() {
        this.owners = new ArrayList<>();
        this.veterinarians = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadTestData();
    }

    private void loadTestData() {
        // Создаем владельцев
        Owner owner1 = new Owner(1, "Dmitriy Rochshupkin", "03-03-03");
        Owner owner2 = new Owner(2, "Manas Agatayev", "10-10-10");
        Owner owner3 = new Owner(3, "Aidos Lazzatbekov", "16-16-16");

        owners.add(owner1);
        owners.add(owner2);
        owners.add(owner3);

        // Создаем питомцев (они автоматически добавляются в список владельца внутри конструктора)
        Pet dog1 = new Pet("Mishka", "dog", 2, owner1);
        Pet cat1 = new Pet("Karamel", "cat", 5, owner2);
        Pet parrot1 = new Pet("Sanyok", "parrot", 2, owner3);

        // Создаем ветеринаров
        Veterinarian vet1 = new Veterinarian(1, "Smith Johnson", "dog", 10, "00-00-01");
        Veterinarian vet2 = new Veterinarian(2, "Lionel Messi", "dog", 3, "00-00-02");
        Veterinarian vet3 = new Veterinarian(3, "Timur Iskakov", "cat", 16, "00-00-03");

        veterinarians.add(vet1);
        veterinarians.add(vet2);
        veterinarians.add(vet3);

        // Создаем процедуры
        Treatment treatment1 = new Surgery(1, owner1, dog1, vet1, "Started", 3);
        Treatment treatment2 = new Vaccination(2, owner2, cat1, vet2, "Completed", "superVaccine");

        treatments.add(treatment1);
        treatments.add(treatment2);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=====================================");
        System.out.println("VETERINARIAN CLINIC MANAGEMENT SYSTEM");
        System.out.println("=====================================");
        System.out.println("1. Add Owner");
        System.out.println("2. View Owners");
        System.out.println("3. Add Pets");
        System.out.println("4. View Pets");
        System.out.println("5. Add Veterinarians");
        System.out.println("6. View Veterinarians");
        System.out.println("7. Add Treatments");
        System.out.println("8. View Treatments");
        System.out.println("9. Polymorphism demo");
        System.out.println("0. Exit");
        System.out.println("=====================================");
        System.out.print("Enter your choice: >>> ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                if (!scanner.hasNextInt()) {
                    System.out.println("❌ Please enter a number!");
                    scanner.next(); // очистка буфера
                    continue;
                }
                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                switch (choice) {
                    case 1: createOwner(); break;
                    case 2: viewOwners(); break;
                    case 3: createPets(); break;
                    case 4: viewPets(); break;
                    case 5: createVeterinarians(); break;
                    case 6: viewVeterinarians(); break;
                    case 7: createTreatments(); break;
                    case 8: viewTreatments(); break;
                    case 9: demonstratePolymorphism(); break;
                    case 0:
                        System.out.println("\nThank you for using this program!");
                        running = false;
                        break;
                    default:
                        System.out.println("Wrong choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void createOwner() {
        System.out.println("\n--- ADD OWNER ---");
        System.out.print("Enter Owner's name: >>> ");
        String name = scanner.nextLine();
        System.out.print("Enter Owner's phone number: >>> ");
        String phone = scanner.nextLine();

        Owner owner = new Owner(owners.size() + 1, name, phone);
        owners.add(owner);
        System.out.println("✅ New Owner created successfully!");
    }

    private void viewOwners() {
        System.out.println("\n--- ALL OWNERS ---");
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        for (Owner o : owners) {
            System.out.println(o.toString());
        }
    }

    private void createPets() {
        try {
            System.out.println("\n--- ADD PET ---");
            System.out.print("Enter Pet's name: >>> ");
            String name = scanner.nextLine();
            System.out.print("Enter Pet's species: >>> ");
            String species = scanner.nextLine();
            System.out.print("Enter Pet's age: >>> ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Pet's owner name: >>> ");
            String ownerName = scanner.nextLine();

            Owner owner = findOwnerByName(ownerName);
            if (owner == null) {
                throw new InvalidInputException("Owner not found: " + ownerName);
            }

            Pet pet = new Pet(name, species, age, owner);
            System.out.println("✅ New Pet created successfully for " + owner.getName());
        } catch (InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void viewPets() {
        System.out.println("\n--- ALL PETS ---");
        boolean found = false;
        for (Owner o : owners) {
            // ВАЖНО: Убедитесь, что в классе Owner есть метод getPets()
            for (Pet p : o.getPets()) {
                System.out.println(p.toString() + " | Owner: " + o.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pets found.");
        }
    }

    private void createVeterinarians() {
        System.out.println("\n--- ADD VETERINARIAN ---");
        System.out.print("Enter Veterinarian's name: >>> ");
        String name = scanner.nextLine();
        System.out.print("Enter Veterinarian's specialization: >>> ");
        String spec = scanner.nextLine();
        System.out.print("Enter Experience (years): >>> ");
        int exp = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Phone: >>> ");
        String phone = scanner.nextLine();

        Veterinarian vet = new Veterinarian(veterinarians.size() + 1, name, spec, exp, phone);
        veterinarians.add(vet);
        System.out.println("✅ Veterinarian added!");
    }

    private void viewVeterinarians() {
        System.out.println("\n--- ALL VETERINARIANS ---");
        for (Veterinarian v : veterinarians) {
            System.out.println(v.toString());
        }
    }

    private void createTreatments() {
        try {
            System.out.println("\n--- ADD TREATMENT ---");
            System.out.print("Owner name: >>> ");
            String ownerName = scanner.nextLine();
            Owner owner = findOwnerByName(ownerName);
            if (owner == null) throw new InvalidInputException("Owner not found!");

            System.out.print("Vet name: >>> ");
            String vetName = scanner.nextLine();
            Veterinarian vet = findVetByName(vetName);
            if (vet == null) throw new InvalidInputException("Vet not found!");

            System.out.print("Pet name: >>> ");
            String petName = scanner.nextLine();
            Pet pet = findPetByName(owner, petName);
            if (pet == null) throw new InvalidInputException("Pet not found!");

            System.out.print("Type (Vaccination/Surgery): >>> ");
            String type = scanner.nextLine();

            if (type.equalsIgnoreCase("Vaccination")) {
                System.out.print("Vaccine name: >>> ");
                String vaccine = scanner.nextLine();
                treatments.add(new Vaccination(treatments.size() + 1, owner, pet, vet, "Scheduled", vaccine));
            } else if (type.equalsIgnoreCase("Surgery")) {
                System.out.print("Difficulty (1-5): >>> ");
                int diff = scanner.nextInt(); scanner.nextLine();
                treatments.add(new Surgery(treatments.size() + 1, owner, pet, vet, "Scheduled", diff));
            }
            System.out.println("✅ Treatment created!");
        } catch (InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void viewTreatments() {
        System.out.println("\n--- ALL TREATMENTS ---");
        for (Treatment t : treatments) {
            System.out.println(t.toString());
            // Проверка подклассов для вывода специфичных полей
            if (t instanceof Vaccination) {
                System.out.println("   [Type: Vaccination, Vaccine: " + ((Vaccination) t).getVaccineName() + "]");
            } else if (t instanceof Surgery) {
                System.out.println("   [Type: Surgery, Difficulty: " + ((Surgery) t).getDifficulty() + "]");
            }
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Treatment t : treatments) {
            t.completeTreatment(); // Вызов переопределенного метода
        }
    }

    // Вспомогательные методы поиска
    private Owner findOwnerByName(String name) {
        for (Owner o : owners) {
            if (o.getName().equalsIgnoreCase(name)) return o;
        }
        return null;
    }

    private Veterinarian findVetByName(String name) {
        for (Veterinarian v : veterinarians) {
            if (v.getName().equalsIgnoreCase(name)) return v;
        }
        return null;
    }

    private Pet findPetByName(Owner owner, String name) {
        for (Pet p : owner.getPets()) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
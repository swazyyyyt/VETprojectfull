package VETprojectfull.menu;

import VETprojectfull.database.*;
import VETprojectfull.model.*;
import VETprojectfull.exception.InvalidInputException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClinicMenu implements Menu {
    private final ClientDAO clientDAO;
    private final PetDAO petDAO;
    private final VeterinarianDAO veterinarianDAO;
    private final Scanner scanner;

    public ClinicMenu() {
        this.clientDAO = new ClientDAO();
        this.petDAO = new PetDAO();
        this.veterinarianDAO = new VeterinarianDAO();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("  VET CLINIC MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("CLIENTS (Owners):");
        System.out.println("1. Add Client           2. View All Clients");
        System.out.println("3. Update Client        4. Delete Client");
        System.out.println("\nPETS:");
        System.out.println("5. Add Pet              6. View All Pets");
        System.out.println("7. Update Pet           8. Delete Pet");
        System.out.println("\nVETERINARIANS:");
        System.out.println("9. Add Veterinarian     10. View All Vets");
        System.out.println("11. Update Vet          12. Delete Vet");
        System.out.println("\nSEARCH:");
        System.out.println("13. Search Client       14. Search Pet");
        System.out.println("15. Search Vet");
        System.out.println("\n0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice >>> ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addClient();
                    case 2 -> viewAllClients();
                    case 3 -> updateClient();
                    case 4 -> deleteClient();
                    case 5 -> addPet();
                    case 6 -> viewAllPets();
                    case 7 -> updatePet();
                    case 8 -> deletePet();
                    case 9 -> addVeterinarian();
                    case 10 -> viewAllVeterinarians();
                    case 11 -> updateVeterinarian();
                    case 12 -> deleteVeterinarian();
                    case 13 -> searchClient();
                    case 14 -> searchPet();
                    case 15 -> searchVet();
                    case 0 -> {
                        System.out.println("\nThank you for using VET Clinic System! Goodbye! 🐾");
                        running = false;
                    }
                    default -> System.out.println("❌ Invalid choice! Please try again.");
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    // ==================== CLIENT OPERATIONS ====================

    private void addClient() {
        try {
            System.out.println("\n--- ADD CLIENT (Owner) ---");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            Client client = new Client(0, name, phone);
            clientDAO.insertClient(client);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAllClients() {
        clientDAO.displayAllClients();
    }

    private void updateClient() {
        try {
            System.out.print("Enter Client ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Client existing = clientDAO.getClientById(id);
            if (existing == null) {
                System.out.println("❌ No client found with ID: " + id);
                return;
            }

            System.out.println("\nCurrent: " + existing.getName() + ", Phone: " + existing.getPhone());

            System.out.print("New Name [" + existing.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                name = existing.getName();
            }

            System.out.print("New Phone [" + existing.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) {
                phone = existing.getPhone();
            }

            Client updated = new Client(id, name, phone);
            clientDAO.updateClient(updated);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deleteClient() {
        try {
            System.out.print("Enter Client ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Client client = clientDAO.getClientById(id);
            if (client == null) {
                System.out.println("❌ No client found with ID: " + id);
                return;
            }

            System.out.println("\nDelete: " + client.getName() + " (ID: " + id + ")");
            System.out.print("⚠️ Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                clientDAO.deleteClient(id);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // ==================== PET OPERATIONS ====================

    private void addPet() {
        try {
            System.out.println("\n--- ADD PET ---");
            System.out.print("Enter pet name: ");
            String name = scanner.nextLine();
            System.out.print("Enter species (Dog/Cat/Bird/etc): ");
            String species = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter owner/client ID: ");
            int ownerId = scanner.nextInt();
            scanner.nextLine();

            Client owner = clientDAO.getClientById(ownerId);
            if (owner == null) {
                System.out.println("❌ Owner not found with ID: " + ownerId);
                return;
            }

            Pet pet = new Pet(name, species, age, owner);
            petDAO.insertPet(pet);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAllPets() {
        petDAO.displayAllPets();
    }

    private void updatePet() {
        try {
            System.out.print("Enter Pet ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("New name: ");
            String name = scanner.nextLine();
            System.out.print("New species: ");
            String species = scanner.nextLine();
            System.out.print("New age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            petDAO.updatePet(id, name, species, age);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deletePet() {
        try {
            System.out.print("Enter Pet ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("⚠️ Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                petDAO.deletePet(id);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // ==================== VETERINARIAN OPERATIONS ====================

    private void addVeterinarian() {
        try {
            System.out.println("\n--- ADD VETERINARIAN ---");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter specialization: ");
            String spec = scanner.nextLine();
            System.out.print("Enter experience (years): ");
            int exp = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            Veterinarian vet = new Veterinarian(0, name, spec, exp, phone);
            veterinarianDAO.insertVeterinarian(vet);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAllVeterinarians(){
        veterinarianDAO.displayAllVeterinarians();
    }

    private void updateVeterinarian() {
        try {
            System.out.print("Enter Vet ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Veterinarian existing = veterinarianDAO.getVeterinarianById(id);
            if (existing == null) {
                System.out.println("❌ No veterinarian found with ID: " + id);
                return;
            }

            System.out.println("\nCurrent: " + existing.getName());

            System.out.print("New Name [" + existing.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                name = existing.getName();
            }

            System.out.print("New Specialization [" + existing.getSpecialization() + "]: ");
            String spec = scanner.nextLine();
            if (spec.trim().isEmpty()) {
                spec = existing.getSpecialization();
            }

            System.out.print("New Experience [" + existing.getExperience() + "]: ");
            String expInput = scanner.nextLine();
            int exp = expInput.trim().isEmpty() ? existing.getExperience() : Integer.parseInt(expInput);

            System.out.print("New Phone [" + existing.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) {
                phone = existing.getPhone();
            }

            Veterinarian updated = new Veterinarian(id, name, spec, exp, phone);
            veterinarianDAO.updateVeterinarian(updated);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deleteVeterinarian() {
        try {
            System.out.print("Enter Vet ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Veterinarian vet = veterinarianDAO.getVeterinarianById(id);
            if (vet == null) {
                System.out.println("❌ No veterinarian found with ID: " + id);
                return;
            }

            System.out.println("\nDelete: Dr. " + vet.getName());
            System.out.print("⚠️ Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                veterinarianDAO.deleteVeterinarian(id);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // ==================== SEARCH OPERATIONS ====================

    private void searchClient() {
        try {
            System.out.print("Enter client name to search: ");
            String name = scanner.nextLine();

            List<Client> results = clientDAO.searchByName(name);

            System.out.println("\n========== SEARCH RESULTS ==========");

            if (results.isEmpty()) {
                System.out.println("No clients found with name: " + name);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    Client c = results.get(i);
                    System.out.println((i + 1) + ". " + c.getName());
                    System.out.println("   ID: " + c.getOwnerId());
                    System.out.println("   Phone: " + c.getPhone());
                    System.out.println();
                }
                System.out.println("Total found: " + results.size());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void searchPet() {
        try {
            System.out.print("Enter pet name to search: ");
            String name = scanner.nextLine();

            List<Pet> results = petDAO.searchByName(name, clientDAO);

            System.out.println("\n========== SEARCH RESULTS ==========");

            if (results.isEmpty()) {
                System.out.println("No pets found with name: " + name);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    Pet p = results.get(i);
                    System.out.println((i + 1) + ". " + p.getName() + " (" + p.getSpecies() + ")");
                    System.out.println("   Age: " + p.getAge());
                    System.out.println("   Owner: " + p.getOwner().getName());
                    System.out.println();
                }
                System.out.println("Total found: " + results.size());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void searchVet() {
        try {
            System.out.print("Enter veterinarian name to search: ");
            String name = scanner.nextLine();

            List<Veterinarian> results = veterinarianDAO.searchByName(name);

            System.out.println("\n========== SEARCH RESULTS ==========");

            if (results.isEmpty()) {
                System.out.println("No veterinarians found with name: " + name);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    Veterinarian v = results.get(i);
                    System.out.println((i + 1) + ". Dr. " + v.getName());
                    System.out.println("   ID: " + v.getVetId());
                    System.out.println("   Specialization: " + v.getSpecialization());
                    System.out.println("   Experience: " + v.getExperience() + " years");
                    System.out.println();
                }
                System.out.println("Total found: " + results.size());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
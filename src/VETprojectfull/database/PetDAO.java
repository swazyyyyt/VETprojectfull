package VETprojectfull.database;

import VETprojectfull.model.Pet;
import VETprojectfull.model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // CREATE - Insert pet
    public void insertPet(Pet pet) {
        String sql = "INSERT INTO pets (name, species, age, owner_id) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSpecies());
            statement.setInt(3, pet.getAge());
            statement.setInt(4, pet.getOwner().getOwnerId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Pet added successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("❌ Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // READ - Display all pets
    public void displayAllPets() {
        String sql = "SELECT p.pet_id, p.name, p.species, p.age, c.name as owner_name " +
                "FROM pets p JOIN clients c ON p.owner_id = c.client_id ORDER BY p.pet_id";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            System.out.println("\n========================================");
            System.out.println("          ALL PETS");
            System.out.println("========================================");

            int count = 0;
            while (rs.next()) {
                count++;
                int id = rs.getInt("pet_id");
                String name = rs.getString("name");
                String species = rs.getString("species");
                int age = rs.getInt("age");
                String ownerName = rs.getString("owner_name");

                System.out.println(count + ". " + name + " (" + species + ")");
                System.out.println("   ID: " + id);
                System.out.println("   Age: " + age + " years");
                System.out.println("   Owner: " + ownerName);
                System.out.println();
            }

            if (count == 0) {
                System.out.println("No pets found.");
            } else {
                System.out.println("Total pets: " + count);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("❌ Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // READ - Get pet by ID (with owner)
    public Pet getPetById(int petId, ClientDAO clientDAO) {
        String sql = "SELECT * FROM pets WHERE pet_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, petId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String species = rs.getString("species");
                int age = rs.getInt("age");
                int ownerId = rs.getInt("owner_id");

                Client owner = clientDAO.getClientById(ownerId);

                rs.close();
                statement.close();

                Pet pet = new Pet(name, species, age, owner);
                return pet;
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("❌ Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    // UPDATE - Update pet
    public boolean updatePet(int petId, String name, String species, int age) {
        String sql = "UPDATE pets SET name = ?, species = ?, age = ? WHERE pet_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, species);
            statement.setInt(3, age);
            statement.setInt(4, petId);

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("✅ Pet updated: " + name);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    // DELETE - Delete pet
    public boolean deletePet(int petId) {
        String sql = "DELETE FROM pets WHERE pet_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, petId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("✅ Pet deleted (ID: " + petId + ")");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    // SEARCH - Search by name
    public List<Pet> searchByName(String name, ClientDAO clientDAO) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return pets;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String petName = rs.getString("name");
                String species = rs.getString("species");
                int age = rs.getInt("age");
                int ownerId = rs.getInt("owner_id");

                Client owner = clientDAO.getClientById(ownerId);
                if (owner != null) {
                    pets.add(new Pet(petName, species, age, owner));
                }
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return pets;
    }
}

package VETprojectfull.database;

import VETprojectfull.model.Veterinarian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianDAO {


    public void insertVeterinarian(Veterinarian vet) {
        String sql = "INSERT INTO veterinarians (name, specialization, experience, phone) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vet.getName());
            statement.setString(2, vet.getSpecialization());
            statement.setInt(3, vet.getExperience());
            statement.setString(4, vet.getPhone());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Veterinarian added successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("❌ Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }


    public void displayAllVeterinarians() {
        String sql = "SELECT * FROM veterinarians ORDER BY vet_id";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            System.out.println("\n========================================");
            System.out.println("       ALL VETERINARIANS");
            System.out.println("========================================");

            int count = 0;
            while (rs.next()) {
                count++;
                int id = rs.getInt("vet_id");
                String name = rs.getString("name");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");
                String phone = rs.getString("phone");

                System.out.println(count + ". Dr. " + name);
                System.out.println("   ID: " + id);
                System.out.println("   Specialization: " + spec);
                System.out.println("   Experience: " + exp + " years");
                System.out.println("   Phone: " + phone);
                if (exp >= 5) {
                    System.out.println("   🏥 Experienced Vet");
                }
                System.out.println();
            }

            if (count == 0) {
                System.out.println("No veterinarians found.");
            } else {
                System.out.println("Total veterinarians: " + count);
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


    public Veterinarian getVeterinarianById(int vetId) {
        String sql = "SELECT * FROM veterinarians WHERE vet_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vetId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("vet_id");
                String name = rs.getString("name");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");
                String phone = rs.getString("phone");

                rs.close();
                statement.close();
                return new Veterinarian(id, name, spec, exp, phone);
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


    public boolean updateVeterinarian(Veterinarian vet) {
        String sql = "UPDATE veterinarians SET name = ?, specialization = ?, experience = ?, phone = ? WHERE vet_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vet.getName());
            statement.setString(2, vet.getSpecialization());
            statement.setInt(3, vet.getExperience());
            statement.setString(4, vet.getPhone());
            statement.setInt(5, vet.getVetId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("✅ Veterinarian updated: " + vet.getName());
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


    public boolean deleteVeterinarian(int vetId) {
        String sql = "DELETE FROM veterinarians WHERE vet_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vetId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("✅ Veterinarian deleted (ID: " + vetId + ")");
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


    public List<Veterinarian> searchByName(String name) {
        List<Veterinarian> vets = new ArrayList<>();
        String sql = "SELECT * FROM veterinarians WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return vets;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("vet_id");
                String vetName = rs.getString("name");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");
                String phone = rs.getString("phone");

                vets.add(new Veterinarian(id, vetName, spec, exp, phone));
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return vets;
    }


    public List<Veterinarian> getAllVeterinarians() {
        List<Veterinarian> vets = new ArrayList<>();
        String sql = "SELECT * FROM veterinarians ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("vet_id");
                String name = rs.getString("name");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");
                String phone = rs.getString("phone");

                vets.add(new Veterinarian(id, name, spec, exp, phone));
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return vets;
    }
}

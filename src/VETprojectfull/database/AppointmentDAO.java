package VETprojectfull.database;

import VETprojectfull.model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // CREATE (Week 7)
    public void addAppointment(Appointment app) {
        String sql = "INSERT INTO appointments (pet_name, owner_name, reason) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, app.getPetName());
            pstmt.setString(2, app.getOwnerName());
            pstmt.setString(3, app.getReason());
            pstmt.executeUpdate();
            System.out.println("✅ Запись успешно создана!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // READ (Week 7)
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getString("pet_name"),
                        rs.getString("owner_name"),
                        rs.getString("reason")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // UPDATE (Week 8)
    public void updateReason(int id, String newReason) {
        String sql = "UPDATE appointments SET reason = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newReason);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("✅ Причина приема обновлена!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // DELETE (Week 8)
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ Запись удалена!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // SEARCH (Week 8)
    public List<Appointment> searchByPetName(String name) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE pet_name ILIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), rs.getString("pet_name"),
                        rs.getString("owner_name"), rs.getString("reason")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
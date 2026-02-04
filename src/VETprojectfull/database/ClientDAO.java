package VETprojectfull.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // CREATE (Week 7) - Вставка данных
    public void insertClient(String name, String phone) {
        String sql = "INSERT INTO clients (name, phone) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.executeUpdate();
            System.out.println("✅ Клиент добавлен!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // READ (Week 7) - Получение всех записей
    public void getAllClients() {
        String sql = "SELECT * FROM clients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") + " | Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // UPDATE (Week 8) - Обновление данных по ID
    public void updateClientPhone(int id, String newPhone) {
        String sql = "UPDATE clients SET phone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhone);
            pstmt.setInt(2, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) System.out.println("✅ Телефон обновлен!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // DELETE (Week 8) - Удаление записи
    public void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ Клиент удален!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // SEARCH (Week 8) - Поиск по имени через ILIKE
    public void findClientByName(String keyword) {
        String sql = "SELECT * FROM clients WHERE name ILIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%"); // Wildcards %
            ResultSet rs = pstmt.executeQuery();
            System.out.println("🔍 Результаты поиска:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
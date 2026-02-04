package VETprojectfull.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/VETproject_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ PostgreSQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("=== STARTING CONNECTION TEST ===");

        Connection conn = getConnection();

        if (conn != null) {
            System.out.println("🎉 SUCCESS! Your Java app can talk to PostgreSQL.");

            closeConnection(conn);
        } else {
            System.out.println("⚠️ ERROR: Could not establish connection. Check if PostgreSQL is running.");
        }

        System.out.println("=== TEST FINISHED ===");
    }
}
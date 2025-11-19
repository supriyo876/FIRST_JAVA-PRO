import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/login_db";
    private static final String USER = "root"; // your DB username
    private static final String PASSWORD = ""; // your DB password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Explicitly load MariaDB driver
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MariaDB successfully!");
        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
        return conn;
    }
}
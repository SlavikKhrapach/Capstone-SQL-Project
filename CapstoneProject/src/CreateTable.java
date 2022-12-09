import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    // Create a new table in Capstone database
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Stuff/College/SDEV280/CapstoneProject/database/capstone.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS schools (\n" +
                "    school_name VARCHAR(100) PRIMARY KEY,\n" +
                "    borough VARCHAR(100),\n" +
                "    building_code VARCHAR(10),\n" +
                "    average_math INT,\n" +
                "    average_reading INT,\n" +
                "    average_writing INT,\n" +
                "    percent_tested FLOAT\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewTable();
    }
}

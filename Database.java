import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:books.db";

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS books (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " title TEXT NOT NULL,\n"
                + " authors TEXT NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
            System.out.println("Table has been created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertBook(String title, String authors) {
        String sql = "INSERT INTO books(title, authors) VALUES(?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, authors);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAllBooks() {
        String sql = "SELECT id, title, authors FROM books";
        
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("title") + "\t" +
                                   rs.getString("authors"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchBooksByAuthor(String author) {
        String sql = "SELECT id, title, authors FROM books WHERE authors LIKE ?";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + author + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("title") + "\t" +
                                   rs.getString("authors"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

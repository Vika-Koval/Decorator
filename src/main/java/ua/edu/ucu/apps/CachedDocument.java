package ua.edu.ucu.apps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CachedDocument extends DocumentDecorator {
    public CachedDocument(Document document) {
        super(document);
    }
    public String parse(String path) {
        String sql = "SELECT parsed_string FROM cache WHERE path = ?";
        String insertSQL = "INSERT INFO (path, parsed_string) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            PreparedStatement selectStmt = conn.prepareStatement(sql);
            PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
            selectStmt.setString(1, path);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                return rs.getString("parsed_string");
            }
            String parsedContent = super.parse(path);
            insertStmt.setString(1, path);
            insertStmt.setString(2, parsedContent);
            insertStmt.executeUpdate();
            return parsedContent;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return super.parse(path);
    }
}
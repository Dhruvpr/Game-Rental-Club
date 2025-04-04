package com.gamerentalclub.controllers;

import com.gamerentalclub.models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for handling user authentication.
 */
public class AuthenticationController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerentalclub";
    private static final String USER = "root";
    private static final String PASS = "Dpsaini@2813"; // Replace with your actual MySQL password

    /**
     * Authenticates a user based on email and password.
     * @param email The user's email.
     * @param password The user's password.
     * @return The authenticated User object, or null if authentication fails.
     */
    public User authenticate(String email, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE Email = ? AND EncryptedPassword = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getString("EncryptedPassword")
                );
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Authentication error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Validates if the user's role matches the selected role.
     * @param user The authenticated user.
     * @param selectedRole The role selected in the UI.
     * @return true if the roles match (case-insensitive), false otherwise.
     */
    public boolean validateRole(User user, String selectedRole) {
        return user != null && user.getRole().equalsIgnoreCase(selectedRole);
    }
}
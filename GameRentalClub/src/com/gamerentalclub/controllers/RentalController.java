package com.gamerentalclub.controllers;

import com.gamerentalclub.models.Rental;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class RentalController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerentalclub";
    private static final String USER = "root";
    private static final String PASS = "Dpsaini@2813"; // Replace with your MySQL password

    private boolean isGameAvailable(int gameId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT StockAvailability FROM Games WHERE GameID = ?")) {
            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("StockAvailability") > 0;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error checking game availability: " + e.getMessage());
            return false;
        }
    }

    private void updateStock(int gameId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE Games SET StockAvailability = StockAvailability - 1 WHERE GameID = ? AND StockAvailability > 0")) {
            stmt.setInt(1, gameId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stock updated for GameID: " + gameId);
            } else {
                System.out.println("Failed to update stock for GameID: " + gameId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating stock: " + e.getMessage());
        }
    }

    public boolean processRental(int gameId, int userId, String rentDate, String expectedReturnDate) {
        Rental rental = new Rental(gameId, userId, rentDate, expectedReturnDate);

        if (!isGameAvailable(gameId)) {
            System.out.println("Game is not available!");
            return false;
        }

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Rentals (GameID, UserID, RentDate, ExpectedReturnDate, Status) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, rental.getGameId());
                stmt.setInt(2, rental.getUserId());
                stmt.setString(3, rental.getRentDate());
                stmt.setString(4, rental.getExpectedReturnDate());
                stmt.setString(5, rental.getStatus());
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Rental processed successfully!");
                    updateStock(gameId); // Update stock after successful rental
                    return true;
                }
                return false;
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Database connection or query error: " + e.getMessage());
            return false;
        }
    }
}
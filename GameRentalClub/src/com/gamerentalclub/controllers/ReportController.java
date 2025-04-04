package com.gamerentalclub.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerentalclub";
    private static final String USER = "root";
    private static final String PASS = "Dpsaini@2813"; // Replace with your MySQL password

    public String generateRentalReport() {
        StringBuilder report = new StringBuilder("Rental Report:\n");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT r.RentalID, r.GameID, g.Title, r.UserID, u.Name, r.RentDate, r.ExpectedReturnDate, r.Status " +
                             "FROM Rentals r JOIN Games g ON r.GameID = g.GameID JOIN Users u ON r.UserID = u.UserID")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                report.append("Rental ID: ").append(rs.getInt("RentalID"))
                        .append(", Game: ").append(rs.getString("Title"))
                        .append(" (ID: ").append(rs.getInt("GameID"))
                        .append("), User: ").append(rs.getString("Name"))
                        .append(" (ID: ").append(rs.getInt("UserID"))
                        .append("), Rent Date: ").append(rs.getString("RentDate"))
                        .append(", Return Date: ").append(rs.getString("ExpectedReturnDate"))
                        .append(", Status: ").append(rs.getString("Status"))
                        .append("\n");
            }
        } catch (SQLException e) {
            report.append("Error generating report: ").append(e.getMessage());
        }
        return report.toString();
    }
}
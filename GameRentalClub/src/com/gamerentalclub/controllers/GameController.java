package com.gamerentalclub.controllers;

import com.gamerentalclub.models.Game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerentalclub";
    private static final String USER = "root";
    private static final String PASS = "Dpsaini@2813"; // Replace with your MySQL password

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Games")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("GameID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("StockAvailability"),
                        rs.getDouble("RentalPrice")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching games: " + e.getMessage());
        }
        return games;
    }

    public List<Game> getGamesByGenre(String genre) {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Games WHERE Genre = ?")) {
            stmt.setString(1, genre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("GameID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("StockAvailability"),
                        rs.getDouble("RentalPrice")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching games by genre: " + e.getMessage());
        }
        return games;
    }
}
package com.gamerentalclub.ui;

import com.gamerentalclub.controllers.GameController;
import com.gamerentalclub.models.Game;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameCatalog extends Application {
    private GameController gameController = new GameController();

    @Override
    public void start(Stage primaryStage) {
        HBox navBar = NavigationBar.createNavBar("Game Catalog", primaryStage);

        Label titleLabel = new Label("Game Catalog");
        Label searchLabel = new Label("Search by Genre:");
        ComboBox<String> genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll("All", "Action", "Adventure", "RPG", "Sports");
        genreComboBox.setValue("All");
        Button searchButton = new Button("Search");
        ListView<String> gameListView = new ListView<>();
        Button rentButton = new Button("Rent Selected Game");
        Button backButton = new Button("Back");

        // Load all games initially
        updateGameList(gameListView, "All");

        searchButton.setOnAction(e -> {
            String selectedGenre = genreComboBox.getValue();
            updateGameList(gameListView, selectedGenre);
        });

        rentButton.setOnAction(e -> {
            String selectedGame = gameListView.getSelectionModel().getSelectedItem();
            if (selectedGame != null) {
                String[] parts = selectedGame.split(" - ");
                int gameId = Integer.parseInt(parts[0].split(": ")[1]);
                new MainApp(gameId).start(new Stage());
                primaryStage.close();
            } else {
                System.out.println("Please select a game to rent.");
            }
        });

        backButton.setOnAction(e -> {
            new CustomerPanel().start(new Stage());
            primaryStage.close();
        });

        HBox searchBox = new HBox(10, searchLabel, genreComboBox, searchButton);
        VBox root = new VBox(10, navBar, titleLabel, searchBox, gameListView, rentButton, backButton);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Game Rental Club - Game Catalog");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateGameList(ListView<String> gameListView, String genre) {
        gameListView.getItems().clear();
        System.out.println("Updating game list for genre: " + genre);
        List<Game> games = genre.equals("All") ? gameController.getAllGames() : gameController.getGamesByGenre(genre);
        System.out.println("Games retrieved: " + games.size());
        for (Game game : games) {
            String gameInfo = "GameID: " + game.getGameId() + " - " + game.getTitle() + " (" + game.getGenre() + ") - Stock: " + game.getStockAvailability() + " - Price: $" + game.getRentalPrice();
            System.out.println("Adding game to list: " + gameInfo);
            gameListView.getItems().add(gameInfo);
        }
        gameListView.refresh();
        System.out.println("ListView items after update: " + gameListView.getItems().size());
    }
}
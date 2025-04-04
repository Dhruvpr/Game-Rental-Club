package com.gamerentalclub.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NavigationBar {
    public static HBox createNavBar(String currentPage, Stage primaryStage) {
        HBox navBar = new HBox(10);
        Button homeButton = new Button("Home");
        Button gamesButton = new Button("Games");
        Button rentalsButton = new Button("Rentals");
        Button profileButton = new Button("Profile");

        homeButton.setOnAction(e -> {
            new CustomerPanel().start(new Stage());
            primaryStage.close();
        });
        gamesButton.setOnAction(e -> {
            new GameCatalog().start(new Stage());
            primaryStage.close();
        });
        rentalsButton.setOnAction(e -> System.out.println("Rentals page not implemented yet."));
        profileButton.setOnAction(e -> System.out.println("Profile page not implemented yet."));

        navBar.getChildren().addAll(homeButton, gamesButton, rentalsButton, profileButton);
        return navBar;
    }
}
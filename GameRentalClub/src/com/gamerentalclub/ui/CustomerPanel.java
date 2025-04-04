package com.gamerentalclub.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox; // Added this import
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerPanel extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox navBar = NavigationBar.createNavBar("Customer Panel", primaryStage);

        Label titleLabel = new Label("Customer Panel");
        Button browseGamesButton = new Button("Browse Games");
        Button rentalHistoryButton = new Button("Rental History");
        Button manageReservationsButton = new Button("Manage Reservations");
        Button logoutButton = new Button("Logout");

        browseGamesButton.setOnAction(e -> {
            new GameCatalog().start(new Stage());
            primaryStage.close();
        });
        rentalHistoryButton.setOnAction(e -> System.out.println("Rental history not implemented yet."));
        manageReservationsButton.setOnAction(e -> System.out.println("Reservations not implemented yet."));
        logoutButton.setOnAction(e -> {
            new LoginApp().start(new Stage());
            primaryStage.close();
        });

        VBox root = new VBox(10, navBar, titleLabel, browseGamesButton, rentalHistoryButton, manageReservationsButton, logoutButton);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Game Rental Club - Customer Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
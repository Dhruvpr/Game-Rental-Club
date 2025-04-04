package com.gamerentalclub.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox; // Added this import
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaffDashboard extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox navBar = NavigationBar.createNavBar("Staff Dashboard", primaryStage);

        Label titleLabel = new Label("Staff Dashboard");
        Button processRentalsButton = new Button("Process Rentals");
        Button trackOverdueButton = new Button("Track Overdue Rentals");
        Button manageCustomersButton = new Button("Manage Customers");
        Button logoutButton = new Button("Logout");

        processRentalsButton.setOnAction(e -> {
            new MainApp().start(new Stage());
            primaryStage.close();
        });
        trackOverdueButton.setOnAction(e -> System.out.println("Overdue tracking not implemented yet."));
        manageCustomersButton.setOnAction(e -> System.out.println("Customer management not implemented yet."));
        logoutButton.setOnAction(e -> {
            new LoginApp().start(new Stage());
            primaryStage.close();
        });

        VBox root = new VBox(10, navBar, titleLabel, processRentalsButton, trackOverdueButton, manageCustomersButton, logoutButton);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Game Rental Club - Staff Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
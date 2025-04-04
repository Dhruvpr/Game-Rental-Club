package com.gamerentalclub.ui;

import com.gamerentalclub.controllers.ReportController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox; // Added this import
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard extends Application {
    private ReportController reportController = new ReportController();

    @Override
    public void start(Stage primaryStage) {
        HBox navBar = NavigationBar.createNavBar("Admin Dashboard", primaryStage);

        Label titleLabel = new Label("Admin Dashboard");
        Button manageUsersButton = new Button("Manage Users");
        Button manageInventoryButton = new Button("Manage Inventory");
        Button viewAnalyticsButton = new Button("View Analytics");
        TextArea analyticsArea = new TextArea();
        analyticsArea.setEditable(false);
        analyticsArea.setPrefHeight(200);
        Button systemSettingsButton = new Button("System Settings");
        Button logoutButton = new Button("Logout");

        manageUsersButton.setOnAction(e -> System.out.println("User management not implemented yet."));
        manageInventoryButton.setOnAction(e -> System.out.println("Inventory management not implemented yet."));
        viewAnalyticsButton.setOnAction(e -> {
            String report = reportController.generateRentalReport();
            analyticsArea.setText(report);
        });
        systemSettingsButton.setOnAction(e -> System.out.println("System settings not implemented yet."));
        logoutButton.setOnAction(e -> {
            new LoginApp().start(new Stage());
            primaryStage.close();
        });

        VBox root = new VBox(10, navBar, titleLabel, manageUsersButton, manageInventoryButton, viewAnalyticsButton, analyticsArea, systemSettingsButton, logoutButton);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Game Rental Club - Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
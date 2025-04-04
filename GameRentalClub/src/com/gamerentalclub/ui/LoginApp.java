package com.gamerentalclub.ui;

import com.gamerentalclub.controllers.AuthenticationController;
import com.gamerentalclub.models.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApp extends Application {
    private AuthenticationController authController = new AuthenticationController();

    @Override
    public void start(Stage primaryStage) {
        HBox navBar = new HBox(10);
        Label navLabel = new Label("Game Rental Club");
        navBar.getChildren().add(navLabel);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label roleLabel = new Label("Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Staff", "Customer");
        roleComboBox.setValue("Customer");
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        Button loginButton = new Button("Login");
        Label resultLabel = new Label("");

        forgotPasswordLink.setOnAction(e -> resultLabel.setText("Password recovery not implemented yet."));

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String selectedRole = roleComboBox.getValue();

            User user = authController.authenticate(email, password);
            if (authController.validateRole(user, selectedRole)) {
                resultLabel.setText("Login successful! Role: " + selectedRole);
                switch (selectedRole) {
                    case "Admin":
                        new AdminDashboard().start(new Stage());
                        break;
                    case "Staff":
                        new StaffDashboard().start(new Stage());
                        break;
                    case "Customer":
                        new CustomerPanel().start(new Stage());
                        break;
                }
                primaryStage.close();
            } else {
                resultLabel.setText("Login failed! Invalid credentials or role.");
            }
        });

        VBox root = new VBox(10, navBar, emailLabel, emailField, passwordLabel, passwordField, roleLabel, roleComboBox, forgotPasswordLink, loginButton, resultLabel);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Game Rental Club - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
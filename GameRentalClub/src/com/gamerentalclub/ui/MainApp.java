package com.gamerentalclub.ui;

import com.gamerentalclub.controllers.RentalController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox; // Added this import
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private RentalController controller = new RentalController();
    private int preFilledGameId = -1;

    public MainApp() {
    }

    public MainApp(int gameId) {
        this.preFilledGameId = gameId;
    }

    @Override
    public void start(Stage primaryStage) {
        HBox navBar = NavigationBar.createNavBar("Rental Page", primaryStage);

        Label gameIdLabel = new Label("Game ID:");
        TextField gameIdField = new TextField(preFilledGameId == -1 ? "1" : String.valueOf(preFilledGameId));
        Label userIdLabel = new Label("User ID:");
        TextField userIdField = new TextField("1");
        Label rentDateLabel = new Label("Rent Date (YYYY-MM-DD):");
        TextField rentDateField = new TextField("2025-04-03");
        Label returnDateLabel = new Label("Return Date (YYYY-MM-DD):");
        TextField returnDateField = new TextField("2025-04-10");
        Button rentButton = new Button("Rent Game");
        Label resultLabel = new Label("");

        rentButton.setOnAction(e -> {
            int gameId = Integer.parseInt(gameIdField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            String rentDate = rentDateField.getText();
            String returnDate = returnDateField.getText();

            boolean success = controller.processRental(gameId, userId, rentDate, returnDate);
            resultLabel.setText(success ? "Rental Confirmed!" : "Rental Failed!");
        });

        VBox root = new VBox(10, navBar, gameIdLabel, gameIdField, userIdLabel, userIdField,
                rentDateLabel, rentDateField, returnDateLabel, returnDateField, rentButton, resultLabel);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Game Rental Club");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        LoginApp.launch(LoginApp.class, args);
    }
}
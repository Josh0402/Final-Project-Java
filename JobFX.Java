//Folder where I kept all files but can be changed to your own, just save the files in a separate folder if needed.
package com.example.demo;
//GUI application to test users wage and overall balance
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
//User establishes the functions for the JavaFX
public class JobFX extends Application {
    private Button calculateWageButton;
    private Button viewBalancesButton;
    // Create an instance of JobFinal to represent the user's account
    private JobFinal userAccount;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User's Account");

        // Create a default user account for testing
        createButtons();
        createDefaultUserAccount();

        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.getChildren().addAll(calculateWageButton, viewBalancesButton);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    // Establishes buttons that are to be used
    // Event one is calculating Users wage and presenting that information
    // Event two shows the User how much money they have in their overall account
    private void createButtons() {
        calculateWageButton = createButton("Calculate Wage", "Calculating Wage.");
        viewBalancesButton = createButton("View Balances", "View Balances.");

        calculateWageButton.setOnAction(event -> {
            if (userAccount != null) {
                userAccount.wage();
            }
        });

        // Correlate information off of what was provided for the User
        viewBalancesButton.setOnAction(event -> {
            if (userAccount != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Account Balances");
                alert.setHeaderText(null);
                alert.setContentText("Savings Balance: " + userAccount.getSavingsBalance() +
                        "\nSpending Balance: " + userAccount.getSpendingBalance());
                alert.showAndWait();
            }
        });
    }

    private Button createButton(String label, String message) {
        Button button = new Button(label);
        button.setOnAction(event -> System.out.println(message));
        return button;
    }

    // Create a default user account for testing
    // This instance uses acc[0] but can be switched out for any of the other acc[]
    private void createDefaultUserAccount() {
        userAccount = new JobFinal("Joshua", "Doctor", 1, 25, 75000.00, 100.00);
    }
}

package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private Button registerButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    private Database db = new Database();
    
    // Handle Register Button Action
    @FXML
    public void handleRegister(ActionEvent event) {
        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Registration Error", "Please fill out all fields.");
            return;
        }

        try {
            // Call the database function to create the user
            db.createUser(username, password, email, fullName, "Traveler"); // Assuming "user" is the default role
            // Show success message
            showAlert(AlertType.INFORMATION, "Registration Successful", "User registered successfully!");

            // Clear fields after registration
            fullNameField.clear();
            usernameField.clear();
            emailField.clear();
            passwordField.clear();

            // Optionally, redirect the user to the login page
            // Redirect logic here

        } catch (SQLException e) {
            // Show an error message if something goes wrong with the database operation
            showAlert(AlertType.ERROR, "Registration Error", "There was an issue registering the user. Please try again later.");
            e.printStackTrace();  // Log the exception for debugging purposes
        }
    }


    // Handle Go Back Button Action
    @FXML
    public void handleGoBack(ActionEvent event) {
        try {
            // Load the Login FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            // Get the current Stage and set the new Scene
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to show alerts
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

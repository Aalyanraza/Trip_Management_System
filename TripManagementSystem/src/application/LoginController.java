package application;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    private Database db = new Database();

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = emailField.getText();
        String password = passwordField.getText();

        // Check if the credentials are for the admin
        if ("admin".equals(username) && "admin".equals(password)) {
            try {
                // Load the Admin Dashboard FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                Parent root = loader.load();

                // Get the current Stage and set the new Scene
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Admin Dashboard");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Create a User object
        User user = new User(0, "", "", null, db);

        // Use the login method from the User class
        if (user.login(username, password)) {
            try {
                // Load the Dashboard FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent root = loader.load();

                DashboardController dashboardController = loader.getController();

                // Pass the userId to the DashboardController
                dashboardController.setUserId(user.getUserId());
                
                // Get the current Stage and set the new Scene
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("User Dashboard");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Show an alert for invalid credentials
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        try {
            // Load the Registration FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = loader.load();

            // Get the current Stage and set the new Scene
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Register");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

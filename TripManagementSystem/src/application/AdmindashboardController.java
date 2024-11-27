package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdmindashboardController {

    @FXML
    private Button viewFeedbackButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button adminSettingsButton;

    // Handle View Feedback Button
    @FXML
    public void handleViewFeedback(ActionEvent event) {
        try {
            // Load the View Feedback FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Veiwfeedback.fxml"));
            Parent root = loader.load();

            // Get the current Stage and set the new Scene
            Stage stage = (Stage) viewFeedbackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Feedback");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to load feedback page.");
        }
    }

    // Handle Logout Button
    @FXML
    public void handleLogout(ActionEvent event) {
        // Simulate logging out (e.g., clearing session data)
        System.out.println("Logging out...");

        // Show success message
        showAlert(AlertType.INFORMATION, "Logged Out", "You have been logged out successfully.");

        // Redirect to the login page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle Admin Settings Button
    @FXML
    public void handleAdminSettings(ActionEvent event) {
        try {
            // Load the Admin Settings FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Adminsettings.fxml"));
            Parent root = loader.load();

            // Get the current Stage and set the new Scene
            Stage stage = (Stage) adminSettingsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Settings");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to load admin settings page.");
        }
    }

    // Helper method to show alert dialogs
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

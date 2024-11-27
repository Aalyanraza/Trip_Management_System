package application;

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

public class ProfilemanagementController {

    @FXML
    private Button backButton;

    @FXML
    private Button deleteProfileButton;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private TextField newPasswordField;

    // Handle Back to Dashboard Button
    @FXML
    public void handleBackToDashboard(ActionEvent event) {
        try {
            // Load the Dashboard FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();

            // Get the current Stage and set the new Scene
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("User Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle Delete Profile Button
    @FXML
    public void handleDeleteProfile(ActionEvent event) {
        // Simulate deleting the profile (In a real app, you'd delete the user from a database)
        System.out.println("Profile deleted");

        // Show success message
        showAlert(AlertType.INFORMATION, "Profile Deleted", "Your profile has been deleted successfully.");
    }

    // Handle Change Password
    @FXML
    public void handleChangePassword(ActionEvent event) {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();

        // Check if fields are empty
        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Please fill in both old and new password.");
            return;
        }

        // Simulate password change (In a real application, you would validate the old password and update the database)
        System.out.println("Password changed from " + oldPassword + " to " + newPassword);

        // Clear the input fields after changing password
        oldPasswordField.clear();
        newPasswordField.clear();

        showAlert(AlertType.INFORMATION, "Password Changed", "Your password has been updated successfully.");
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

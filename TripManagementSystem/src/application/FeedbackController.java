package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FeedbackController implements UserIdAware {

    @FXML
    private Button submitButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea feedbackTextArea;
    
    private int userId;
    
    private Database db = new Database();  // Database class to handle DB operations
    
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Handle Submit Button Action
    @FXML
    public void handleSubmit(ActionEvent event) {
        String feedback = feedbackTextArea.getText();

        if (feedback.isEmpty()) {
            showAlert(AlertType.ERROR, "Feedback Error", "Please provide feedback before submitting.");
            return;
        }

        try {
            // Log the feedback to the database
            db.logFeedback(userId, feedback);
            
            // Clear the feedback text area after submission
            feedbackTextArea.clear();

            // Show success alert
            showAlert(AlertType.INFORMATION, "Feedback Submitted", "Your feedback has been submitted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "There was an error saving your feedback.");
        }
    }

    // Handle Back to Dashboard Button Action
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

    // Helper method to show alert dialogs
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

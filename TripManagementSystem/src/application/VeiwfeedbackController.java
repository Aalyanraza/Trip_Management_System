package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VeiwfeedbackController {

    @FXML
    private Button backButton;

//    @FXML
//    private TableView<Feedback> feedbackTable;
//
//    @FXML
//    private TableColumn<Feedback, String> userIdColumn;
//
//    @FXML
//    private TableColumn<Feedback, String> feedbackColumn;

    // Handle Back to Dashboard Button
    @FXML
    public void handleBackToDashboard(ActionEvent event) {
        try {
            // Load the Dashboard FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admindashboard.fxml"));
            Parent root = loader.load();

            // Get the current Stage and set the new Scene
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("User Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to load dashboard.");
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

    // Initialize method to populate the feedback table with data
    @FXML
    public void initialize() {
        // Set up the columns for the feedback table
//        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
//        feedbackColumn.setCellValueFactory(cellData -> cellData.getValue().feedbackProperty());
//
//        // Add some sample data (this would be replaced with actual data from a database)
//        feedbackTable.getItems().add(new Feedback("1", "Great app, easy to use!"));
//        feedbackTable.getItems().add(new Feedback("2", "Needs more features."));
    }
}

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

public class CreateitenaryController implements UserIdAware {

    @FXML
    private Button createButton;

    @FXML
    private Button backToDashboardButton;

    @FXML
    private TextField tripTitleField;

    @FXML
    private TextField startDateField;

    @FXML
    private TextField endDateField;

    @FXML
    private TextField destinationField;
    
    private Database db = new Database();
    
    private int userId;

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Handle Create Itinerary Button Action
    @FXML
    public void handleCreateItinerary(ActionEvent event)  {
        String tripTitle = tripTitleField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        String destination = destinationField.getText();

        if (tripTitle.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || destination.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "All fields are required!");
            return;
        }

        try {
            // Call the database method to create the itinerary
            db.createItinerary(userId, tripTitle, startDate, endDate, destination);

            // Show success message
            showAlert(AlertType.INFORMATION, "Success", "Itinerary created successfully!");

            // Clear fields after creation
            tripTitleField.clear();
            startDateField.clear();
            endDateField.clear();
            destinationField.clear();
        } catch (SQLException e) {
            // Handle SQL exceptions, such as database connectivity issues
            showAlert(AlertType.ERROR, "Database Error", "An error occurred while saving the itinerary.");
            e.printStackTrace();
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
            Stage stage = (Stage) backToDashboardButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
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

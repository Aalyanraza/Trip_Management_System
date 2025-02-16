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

import java.sql.SQLException;
import java.util.List;

public class EdititenariesController implements UserIdAware {

    @FXML
    private TableView<Itinerary> itineraryTable;
    
    @FXML
    private TableColumn<Itinerary, String> titleColumn;
    
    @FXML
    private TableColumn<Itinerary, String> startDateColumn;
    
    @FXML
    private TableColumn<Itinerary, String> endDateColumn;
    
    @FXML
    private TableColumn<Itinerary, String> destinationColumn;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button editButton;

    private int userId;

    private Database db = new Database();  // Database class to handle DB operations
    
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
        loadItineraries(); // Load all itineraries for the user
    }

    // Event Listener for Back to Dashboard Button
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

    // Event Listener for Edit Itinerary Button
    @FXML
    public void handleEditItinerary(ActionEvent event) {
        Itinerary selectedItinerary = itineraryTable.getSelectionModel().getSelectedItem();

        if (selectedItinerary != null) {
            // Logic for editing the selected itinerary
            System.out.println("Editing: " + selectedItinerary.getTripTitle());
            // Show editing form (this can be a new screen or modal)
        } else {
            showAlert(AlertType.WARNING, "No Itinerary Selected", "Please select an itinerary to edit.");
        }
    }

    // Event Listener for Delete Itinerary Button
    @FXML
    public void handleDeleteItinerary(ActionEvent event) {
        Itinerary selectedItinerary = itineraryTable.getSelectionModel().getSelectedItem();

        if (selectedItinerary != null) {
            // Logic for deleting the selected itinerary
            System.out.println("Deleting: " + selectedItinerary.getTripTitle());
            // Optionally, remove from the table and backend (database)
        } else {
            showAlert(AlertType.WARNING, "No Itinerary Selected", "Please select an itinerary to delete.");
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

    // Method to load itineraries from the database
    private void loadItineraries() {
        try {
            // Fetch itineraries from the database using the Database class
            List<Itinerary> itineraries = db.getItineraries(userId);
            itineraryTable.getItems().clear();  // Clear the table before adding new items
            itineraryTable.getItems().addAll(itineraries);  // Add the fetched itineraries to the table
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Unable to fetch itineraries from the database.");
        }
    }
}

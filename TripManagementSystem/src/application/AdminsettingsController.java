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

public class AdminsettingsController {

    @FXML
    private Button backButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button deleteItineraryButton;

//    @FXML
//    private TableView<User> userTable;
//
//    @FXML
//    private TableColumn<User, String> userNameColumn;
//
//    @FXML
//    private TableColumn<User, String> emailColumn;
//
//    @FXML
//    private TableView<Itinerary> itineraryTable;
//
//    @FXML
//    private TableColumn<Itinerary, String> itineraryTitleColumn;
//
//    @FXML
//    private TableColumn<Itinerary, Integer> userIDColumn;

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

    // Handle Delete User Button
    @FXML
    public void handleDeleteUser(ActionEvent event) {
//        User selectedUser = userTable.getSelectionModel().getSelectedItem();
//
//        if (selectedUser != null) {
//            // Simulate deleting the user (In a real application, this would interact with a database)
//            System.out.println("User deleted: " + selectedUser.getUserName());
//
//            // Show success message
//            showAlert(AlertType.INFORMATION, "User Deleted", "The selected user has been deleted.");
//            userTable.getItems().remove(selectedUser); // Remove from the table (optional)
//        } else {
//            showAlert(AlertType.WARNING, "No User Selected", "Please select a user to delete.");
//        }
    }

    // Handle Delete Itinerary Button
    @FXML
    public void handleDeleteItinerary(ActionEvent event) {
//        Itinerary selectedItinerary = itineraryTable.getSelectionModel().getSelectedItem();
//
//        if (selectedItinerary != null) {
//            // Simulate deleting the itinerary (In a real application, this would interact with a database)
//            System.out.println("Itinerary deleted: " + selectedItinerary.getTitle());
//
//            // Show success message
//            showAlert(AlertType.INFORMATION, "Itinerary Deleted", "The selected itinerary has been deleted.");
//            itineraryTable.getItems().remove(selectedItinerary); // Remove from the table (optional)
//        } else {
//            showAlert(AlertType.WARNING, "No Itinerary Selected", "Please select an itinerary to delete.");
//        }
    }

    // Helper method to show alert dialogs
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Initialize method to populate the tables with some sample data (this can be fetched from the backend)
    @FXML
    public void initialize() {
//        // Set up the columns for the user table
//        userNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
//        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
//
//        // Set up the columns for the itinerary table
//        itineraryTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
//        userIDColumn.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
//
//        // Add sample users (this should be fetched from the database in a real application)
//        userTable.getItems().add(new User("john_doe", "john@example.com"));
//        userTable.getItems().add(new User("jane_doe", "jane@example.com"));
//
//        // Add sample itineraries (this should be fetched from the database in a real application)
//        itineraryTable.getItems().add(new Itinerary("Trip to Paris", 1));
//        itineraryTable.getItems().add(new Itinerary("Trip to London", 2));
    }
}

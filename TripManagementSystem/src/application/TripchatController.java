package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TripchatController {

    @FXML
    private Button backButton;

    @FXML
    private Button postButton;

//    @FXML
//    private TableView<Itinerary> itineraryTable;
//
//    @FXML
//    private TableColumn<Itinerary, String> itineraryColumn;

    @FXML
    private ListView<String> itineraryListView;

    @FXML
    private TextArea messageInput;

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

    // Handle Post Message Button
    @FXML
    public void handlePostMessage(ActionEvent event) {
        String message = messageInput.getText();

        if (message.isEmpty()) {
            showAlert(AlertType.ERROR, "Message Error", "Please enter a message before posting.");
            return;
        }

        // Simulate posting the message to the selected itinerary (In a real application, this would be sent to a server or stored in a database)
        String selectedItinerary = itineraryListView.getSelectionModel().getSelectedItem();

        if (selectedItinerary == null) {
            showAlert(AlertType.WARNING, "No Itinerary Selected", "Please select an itinerary before posting a message.");
            return;
        }

        // Simulate adding message to the itinerary (this should ideally update a database or backend system)
        System.out.println("Message posted to " + selectedItinerary + ": " + message);

        // Clear the message input field after posting
        messageInput.clear();

        showAlert(AlertType.INFORMATION, "Message Posted", "Your message has been posted successfully.");
    }

    // Helper method to show alert dialogs
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Initialize method to populate the ListView and TableView with data
    @FXML
    public void initialize() {
        // Populate the itinerary list with some sample data (replace with actual backend data)
//        itineraryListView.getItems().addAll("Trip to Paris", "Trip to London", "Trip to New York");

        // Populate the itinerary table with some sample data (replace with actual backend data)
//        itineraryColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
//        itineraryTable.getItems().add(new Itinerary("Trip to Paris", "2024-01-01", "2024-01-10", "Paris"));
//        itineraryTable.getItems().add(new Itinerary("Trip to London", "2024-02-01", "2024-02-10", "London"));
    }
}

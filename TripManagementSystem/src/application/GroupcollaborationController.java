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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GroupcollaborationController {

    @FXML
    private Button backButton;
    
    @FXML
    private Button addButton;

 //   @FXML
//    private TableView<Itinerary> itineraryTable;
//
//    @FXML
//    private TableView<Collaborator> collaboratorsTable;

    @FXML
    private TextField emailField;

    @FXML
    private TextField itineraryTitleField;

//    @FXML
//    private TableColumn<Itinerary, String> itineraryColumn;
//
//    @FXML
//    private TableColumn<Collaborator, String> collaboratorsColumn;

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

    // Handle Add Collaborator Button
    @FXML
    public void handleAddCollaborator(ActionEvent event) {
        String email = emailField.getText();
        String itineraryTitle = itineraryTitleField.getText();

        if (email.isEmpty() || itineraryTitle.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Please fill in both email and itinerary title.");
            return;
        }

//        // Simulate adding collaborator to the table (In real app, this should be saved to a database)
//        Collaborator collaborator = new Collaborator(email, itineraryTitle);
//        collaboratorsTable.getItems().add(collaborator);

        // Clear the input fields after adding
        emailField.clear();
        itineraryTitleField.clear();

        showAlert(AlertType.INFORMATION, "Collaborator Added", "The collaborator has been added successfully.");
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

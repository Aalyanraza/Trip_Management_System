package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button createItineraryButton;
    @FXML
    private Button tripChatsButton;
    @FXML
    private Button viewEditItineraryButton;
    @FXML
    private Button trackExpensesButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private Button groupCollabButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button manageProfileButton;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Helper method to navigate between scenes and send userId
    private void navigateTo(String fxmlFile, String title, ActionEvent event) {
        try {
            // Load the target FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the current Stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Get the controller of the loaded scene
            Object controller = loader.getController();

            // Check if the controller has a method to accept the userId
            if (controller instanceof UserIdAware) {
                ((UserIdAware) controller).setUserId(userId);
            }

            // Set the new Scene
            stage.setScene(new Scene(root));
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Event Listener for Create Itinerary Button
    @FXML
    public void handleCreateItinerary(ActionEvent event) {
        navigateTo("Createitenary.fxml", "Create Itinerary", event);
    }

    // Event Listener for Trip Chats Button
    @FXML
    public void handleTripChats(ActionEvent event) {
        navigateTo("Tripchat.fxml", "Trip Chats", event);
    }

    // Event Listener for View and Edit Itinerary Button
    @FXML
    public void handleViewEditItinerary(ActionEvent event) {
        navigateTo("Edititenaries.fxml", "View/Edit Itinerary", event);
    }

    // Event Listener for Track Expenses Button
    @FXML
    public void handleTrackExpenses(ActionEvent event) {
        navigateTo("Expensetracking.fxml", "Track Expenses", event);
    }

    // Event Listener for Feedback Button
    @FXML
    public void handleFeedback(ActionEvent event) {
        navigateTo("Feedback.fxml", "Feedback", event);
    }

    // Event Listener for Group Collaboration Button
    @FXML
    public void handleGroupCollab(ActionEvent event) {
        navigateTo("Groupcollaboration.fxml", "Group Collaboration", event);
    }

    // Event Listener for Logout Button
    @FXML
    public void handleLogout(ActionEvent event) {
        navigateTo("Login.fxml", "Login", event);
    }

    // Event Listener for Manage Profile Button
    @FXML
    public void handleManageProfile(ActionEvent event) {
        navigateTo("ProfileManagement.fxml", "Manage Profile", event);
    }
}

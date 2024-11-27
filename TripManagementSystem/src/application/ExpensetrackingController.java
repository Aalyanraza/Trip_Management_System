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

public class ExpensetrackingController {

    @FXML
    private Button addExpenseButton;
    
    @FXML
    private Button deleteExpenseButton;
    
    @FXML
    private Button backButton;

    @FXML
    private TextField amountField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField descriptionField;

//    @FXML
//    private TableView<Expense> expenseTable;
//    
//    @FXML
//    private TableColumn<Expense, String> categoryColumn;
//
//    @FXML
//    private TableColumn<Expense, String> descriptionColumn;
//
//    @FXML
//    private TableColumn<Expense, String> amountColumn;
//
//    @FXML
//    private TableView<Itinerary> itineraryTable;

    // Handle Add Expense Button
    @FXML
    public void handleAddExpense(ActionEvent event) {
        String amount = amountField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();

        if (amount.isEmpty() || category.isEmpty() || description.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Please fill in all fields.");
            return;
        }

//        // Simulate adding an expense (In real application, this should be saved in a database)
//        Expense expense = new Expense(category, description, amount);
//        expenseTable.getItems().add(expense);

        // Clear the input fields after adding
        amountField.clear();
        categoryField.clear();
        descriptionField.clear();

        showAlert(AlertType.INFORMATION, "Expense Added", "The expense has been added successfully.");
    }

    // Handle Delete Expense Button
    @FXML
    public void handleDeleteExpense(ActionEvent event) {
//        Expense selectedExpense = expenseTable.getSelectionModel().getSelectedItem();
//
//        if (selectedExpense != null) {
//            expenseTable.getItems().remove(selectedExpense);
//            showAlert(AlertType.INFORMATION, "Expense Deleted", "The selected expense has been deleted.");
//        } else {
//            showAlert(AlertType.WARNING, "No Selection", "Please select an expense to delete.");
//        }
    }

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

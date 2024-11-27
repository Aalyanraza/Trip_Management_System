package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    // List of FXML files to display sequentially
    private final List<String> fxmlFiles = List.of(
        "Login.fxml"
        //"Register.fxml",
        //"Dashboard.fxml"
        //"Createitenary.fxml",
        //"Edititenaries.fxml"
        //"Expensetracking.fxml",
        //"Groupcollaboration.fxml",
        //"Tripchat.fxml",
        //"Feedback.fxml",
        //"Profilemanagement.fxml",
        //"Admindashboard.fxml",
        //"Adminsettings.fxml",
        //"Veiwfeedback.fxml"
        
    );

    private int currentFileIndex = 0; // Track the current FXML file index

    @Override
    public void start(Stage primaryStage) {
        // Start by showing the first FXML file
        loadFXML(primaryStage, fxmlFiles.get(currentFileIndex));
    }

    private void loadFXML(Stage stage, String fxmlFile) {
        try {
            // Load the current FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Create a new Scene and set it on the Stage
            Scene scene = new Scene(root);
            stage.setTitle("Trip Management System");
            stage.setScene(scene);

            // Add a listener to load the next FXML on close
            stage.setOnHidden(event -> showNextFXML());

            // Show the Stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNextFXML() {
        currentFileIndex++;
        if (currentFileIndex < fxmlFiles.size()) {
            // Load the next FXML file in a new Stage
            Stage nextStage = new Stage();
            loadFXML(nextStage, fxmlFiles.get(currentFileIndex));
        } else {
            System.out.println("All FXML files have been displayed.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}














//
//
//public class Main extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setTitle("Trip Management System");
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//		
//	}
//}

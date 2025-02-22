import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXEditorMain extends Application{
	@Override
	public void start(Stage myStage) throws Exception {
		// Create an instance of FXMLLoader
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
		// Load the file
		Parent root = myLoader.load();
		// Retrieve the controller
		FXMLController myController = myLoader.getController();
		// Pass the stage to the controller
		myController.setMyStage(myStage);
		// Alternative approach:
		// ((FXMLController)myLoader.getController()).setMyStage(myStage);
		// Create the scene
		// The constructor takes the root node and the size as arguments
		Scene myScene = new Scene(root, 600, 400);
		// Set the title via the stage
		myStage.setTitle("JavaFX Editor");
		// Set the scene
		myStage.setScene(myScene);
		// Display in full screen mode
		myStage.setMaximized(false);
		// Show the window
		myStage.show();
	}

	public static void main(String[] args) {
		// Start the application
		launch(args);
	}
}

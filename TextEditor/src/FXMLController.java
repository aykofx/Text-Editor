import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLController {
    // The text area
    @FXML private TextArea editor;
    // The stage
    private Stage myStage;

    // Method to exit the application
    @FXML protected void onExitClick(ActionEvent event) {
        Platform.exit();
    }

    // Method to show the open file dialog
    @FXML protected void onLoadClick(ActionEvent event) {
        FileChooser openDialog = new FileChooser();
        openDialog.setTitle("Open File");
        openDialog.setInitialDirectory(new File(System.getProperty("user.home")));
        openDialog.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File file = openDialog.showOpenDialog(myStage);
        if (file != null) {
			editor.setText(readData(file));
		}
    }

    // Method to create a new document
    @FXML protected void onNewClick(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION, "The data will be deleted.");
        alert.setHeaderText("Please Note");
        alert.showAndWait();
        editor.clear();
    }

    // Method to show the save file dialog
    @FXML protected void onSaveClick(ActionEvent event) {
        FileChooser saveDialog = new FileChooser();
        saveDialog.setTitle("Save File");
        saveDialog.setInitialDirectory(new File(System.getProperty("user.home")));
        saveDialog.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File file = saveDialog.showSaveDialog(myStage);
        if (file != null) {
			writeData(file);
		}
    }

    // Method to read data from a file
    private String readData(File fileToRead) {
        int character;
        StringBuilder text = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileToRead)) {
            character = fileReader.read();
            while (character != -1) {
                text.append((char) character);
                character = fileReader.read();
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.INFORMATION, "An error occurred while loading the file.");
            alert.setHeaderText("Please Note");
            alert.showAndWait();
        }
        return text.toString();
    }

    // Method to write data to a file
    private void writeData(File fileToWrite) {
        try (FileWriter fileWriter = new FileWriter(fileToWrite)) {
            fileWriter.write(editor.getText());
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.INFORMATION, "An error occurred while saving the file.");
            alert.setHeaderText("Please Note");
            alert.showAndWait();
        }
    }

    // Methods for cut, copy, and paste
    @FXML private void onCutClick(ActionEvent e) {
        editor.cut();
    }

    @FXML private void onCopyClick(ActionEvent e) {
        editor.copy();
    }

    @FXML private void onPasteClick(ActionEvent e) {
        editor.paste();
    }

    // Method to set the stage
    public void setMyStage(Stage myStage) {
        this.myStage = myStage;
    }
}

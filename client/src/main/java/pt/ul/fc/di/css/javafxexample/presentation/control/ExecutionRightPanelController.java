package pt.ul.fc.di.css.javafxexample.presentation.control;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.ul.fc.di.css.javafxexample.presentation.model.ThesisExecutionModel;

public class ExecutionRightPanelController {
    private ThesisExecutionModel selection;

    @FXML
    private Label noSelectionLabel;
    @FXML
    private VBox detailsBox;
    @FXML
    private Label executionTopicNameLabel;
    @FXML
    private Label advisorNameLabel;
    @FXML
    private Label yearLabel;

    
    @FXML
    private Button newFileButton;

    public void initModel(ObjectProperty<ThesisExecutionModel> selectedItemProperty) {
        
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {    
                System.out.println("selected");            
                selection = newSelection;
                executionTopicNameLabel.setText(String.valueOf(newSelection.getTopic().getTitle()));
                advisorNameLabel.setText(String.valueOf(newSelection.getInternalAdvisor().getName()));
                yearLabel.setText(String.valueOf(newSelection.getYearOfExecution()));
                noSelectionLabel.setVisible(false);
                detailsBox.setVisible(true);
            } else {
                noSelectionLabel.setVisible(true);
                detailsBox.setVisible(false);
            }
        });
    }
    @FXML
    public void handleSubmitFile() {
        try {
            String popupFxml = "/pt/ul/fc/di/css/javafxexample/presentation/view/SubmitFilePopup.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(popupFxml));
            Parent root = loader.load();
    
            // Create a new stage for the popup dialog
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UNDECORATED); // Use UNDECORATED to prevent moving
            dialogStage.setTitle("Confirm Cancel");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
 
    
            // Center the dialog on the parent window
            Stage primaryStage = (Stage) newFileButton.getScene().getWindow();
            scene.getWindow().setOnShown(event -> {
                dialogStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - scene.getWidth() / 2);
                dialogStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - scene.getHeight() / 2);
            });
    
            // Set the dialog stage in the controller
            SubmitFilePopupController controller = loader.getController();
            controller.setDialogStage(dialogStage);
    
            controller.setSelectedId(selection.getId());
    
            // Show the overlay
            MainControllerSingleton.mainController.showOverlay();
    
            // Show the dialog and wait for it to be closed
            dialogStage.showAndWait();
    
            // Hide the overlay
            MainControllerSingleton.mainController.hideOverlay();
    
            // Check if the action was confirmed
            if (controller.isConfirmed()) {
                System.out.println("Cancel button pressed for application with id: " + selection.getId());
                MainControllerSingleton.mainController.showExecutions();
                // Perform the cancellation logic here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

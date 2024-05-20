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
import pt.ul.fc.di.css.javafxexample.presentation.model.ApplicationModel;

public class ApplicationRightPanelController {
    @FXML
    private Label noSelectionLabel;
    @FXML
    private VBox detailsBox;
    @FXML
    private Label applicationDetailsLabel;
    @FXML
    private Button cancelButton;

    @FXML
    private void initialize() {
        // Set initial visibility
        noSelectionLabel.setVisible(true);
        detailsBox.setVisible(false);
    }

    @FXML
private void handleCancelButton() {
    try {
        String popupFxml = "/pt/ul/fc/di/css/javafxexample/presentation/view/ApplicationDeletionPopup.fxml";
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
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        scene.getWindow().setOnShown(event -> {
            dialogStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - scene.getWidth() / 2);
            dialogStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - scene.getHeight() / 2);
        });

        // Set the dialog stage in the controller
        ConfirmationPopupController controller = loader.getController();
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
            MainControllerSingleton.mainController.showApplicationList();
            // Perform the cancellation logic here
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private ApplicationModel selection;

    public void initModel(ObjectProperty<ApplicationModel> selectedItemProperty) {
        
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                noSelectionLabel.setVisible(false);
                detailsBox.setVisible(true);                
                applicationDetailsLabel.setText(newSelection.getTopic().getTitle());
                selection = newSelection;
            } else {
                noSelectionLabel.setVisible(true);
                detailsBox.setVisible(false);
                applicationDetailsLabel.setText("");
            }
        });
    }
}

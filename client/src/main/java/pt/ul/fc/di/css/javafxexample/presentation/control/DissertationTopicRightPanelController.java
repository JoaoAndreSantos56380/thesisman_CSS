package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.beans.property.ObjectProperty;
import pt.ul.fc.di.css.javafxexample.presentation.model.DissertationTopicModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.MastersModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.ProfessorModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.AppUserModel;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class DissertationTopicRightPanelController {
    @FXML
    private Label noSelectionLabel;
    @FXML
    private VBox detailsBox;
    @FXML
    private Label idLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label submitterLabel;
    @FXML
    private Label compatibleMastersLabel;
    @FXML
    private Label dissertationTypeLabel;

    @FXML
    private Button applyButton;
    
    @FXML
private void handleApplyButton() {
    try {
        String popupFxml = "/pt/ul/fc/di/css/javafxexample/presentation/view/ApplyToTopicPopup.fxml";
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
        Stage primaryStage = (Stage) applyButton.getScene().getWindow();
        scene.getWindow().setOnShown(event -> {
            dialogStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - scene.getWidth() / 2);
            dialogStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - scene.getHeight() / 2);
        });

        // Set the dialog stage in the controller
        ApplyToTopicPopupController controller = loader.getController();
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
            MainControllerSingleton.mainController.showDissertationTopicList();
            // Perform the cancellation logic here
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private DissertationTopicModel selection;

    public void initModel(ObjectProperty<DissertationTopicModel> selectedItemProperty) {
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                noSelectionLabel.setVisible(false);
                detailsBox.setVisible(true);

                idLabel.setText(String.valueOf(newSelection.getId()));
                typeLabel.setText(String.valueOf(newSelection.getType()));
                titleLabel.setText(newSelection.getTitle());
                descriptionLabel.setText(newSelection.getDescription());
                salaryLabel.setText(String.valueOf(newSelection.getSalary()));

                AppUserModel submitter = newSelection.getSubmitter();
                if (submitter != null) {
                    submitterLabel.setText(submitter.getName());
                } else {
                    submitterLabel.setText("No submitter");
                }
                selection = newSelection;
                Set<MastersModel> compatibleMasters = newSelection.getCompatibleMasters();
                if (compatibleMasters != null && !compatibleMasters.isEmpty()) {
                    String mastersList = compatibleMasters.stream()
                            .map(MastersModel::getName)
                            .collect(Collectors.joining(", "));
                    compatibleMastersLabel.setText(mastersList);
                } else {
                    compatibleMastersLabel.setText("No compatible masters");
                }
                if (submitter instanceof ProfessorModel) {
                    dissertationTypeLabel.setText("Dissertation");
                } else {
                    dissertationTypeLabel.setText("Project");
                }
            } else {
                noSelectionLabel.setVisible(true);
                detailsBox.setVisible(false);
            }
        });
    }
}

package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
        System.out.println("Cancel button pressed for application with id: " + selection.getId());
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

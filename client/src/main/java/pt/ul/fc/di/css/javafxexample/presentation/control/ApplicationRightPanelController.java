package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.beans.property.ObjectProperty;
import pt.ul.fc.di.css.javafxexample.presentation.model.ApplicationModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.MastersModel;

public class ApplicationRightPanelController {
    @FXML
    private Label applicationDetailsLabel;

    public void initModel(ObjectProperty<ApplicationModel> selectedItemProperty) {
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                applicationDetailsLabel.setText("Master: " + newSelection.getTopic().getTitle());
            } else {
                applicationDetailsLabel.setText("No application selected");
            }
        });
    }}

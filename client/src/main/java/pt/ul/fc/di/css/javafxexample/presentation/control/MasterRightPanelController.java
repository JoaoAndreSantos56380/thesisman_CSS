package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.beans.property.ObjectProperty;
import pt.ul.fc.di.css.javafxexample.presentation.model.MastersModel;

public class MasterRightPanelController {
    @FXML
    private Label masterDetailsLabel;

    public void initModel(ObjectProperty<MastersModel> selectedItemProperty) {
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                masterDetailsLabel.setText("Master: " + newSelection.getName());
            } else {
                masterDetailsLabel.setText("No master selected");
            }
        });
    }}

package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.beans.property.ObjectProperty;
import pt.ul.fc.di.css.javafxexample.presentation.model.DissertationTopicModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.MastersModel;

public class DissertationTopicRightPanelController {
    @FXML
    private Label dissertationDetailsLabel;

    public void initModel(ObjectProperty<DissertationTopicModel> selectedItemProperty) {
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dissertationDetailsLabel.setText("Dissertation Topic: " + newSelection.getDescription());
            } else {
                dissertationDetailsLabel.setText("No dissertation topic selected");
            }
        });
    }}

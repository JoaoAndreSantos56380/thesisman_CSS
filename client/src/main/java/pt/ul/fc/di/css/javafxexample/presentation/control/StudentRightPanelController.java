package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pt.ul.fc.di.css.javafxexample.presentation.model.StudentModel;

public class StudentRightPanelController {
    @FXML
    private Label studentDetailsLabel;

    public void initModel(ObjectProperty<StudentModel> selectedItemProperty) {
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                studentDetailsLabel.setText("Student: " + newSelection.getName());
            } else {
                studentDetailsLabel.setText("No student selected");
            }
        });
    }

    // Add methods to update details as needed
}

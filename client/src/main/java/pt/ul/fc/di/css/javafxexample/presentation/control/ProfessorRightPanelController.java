package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pt.ul.fc.di.css.javafxexample.presentation.model.ProfessorModel;

public class ProfessorRightPanelController {
    @FXML
    private Label professorDetailsLabel;

    public void initModel(ObjectProperty<ProfessorModel> selectedItemProperty) {
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                professorDetailsLabel.setText("Professor: " + newSelection.getName());
            } else {
                professorDetailsLabel.setText("No professor selected");
            }
        });
    }
}

package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
}

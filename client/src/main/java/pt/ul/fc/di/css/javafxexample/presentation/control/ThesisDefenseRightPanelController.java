package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pt.ul.fc.di.css.javafxexample.presentation.model.ThesisDefenseModel;

public class ThesisDefenseRightPanelController {

    @FXML
    private Label topicLabel;

    @FXML
    private Label gradeLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private VBox detailsBox;

    @FXML 
    private Label noSelectionLabel;

    ThesisDefenseModel selection;
    public void initModel(ObjectProperty<ThesisDefenseModel> selectedItemProperty) {
        
        selectedItemProperty.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {    
                selection = newSelection;
                topicLabel.setText(String.valueOf(newSelection.getThesisExecution().getTopic().getTitle()));
                
                gradeLabel.setText(String.valueOf(newSelection.getGrade()));
                locationLabel.setText(String.valueOf(newSelection.getLocation()));

                timeLabel.setText(newSelection.getTime().toString());
                noSelectionLabel.setVisible(false);

                detailsBox.setVisible(true);
            } else {
                noSelectionLabel.setVisible(true);
                detailsBox.setVisible(false);
            }
        });
    }
}

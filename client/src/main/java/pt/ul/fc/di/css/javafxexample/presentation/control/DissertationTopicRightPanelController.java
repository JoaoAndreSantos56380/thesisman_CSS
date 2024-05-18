package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.beans.property.ObjectProperty;
import pt.ul.fc.di.css.javafxexample.presentation.model.DissertationTopicModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.MastersModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.AppUserModel;

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
    private Button applyButton;
    
    @FXML
    private void handleApplyButton() {
        System.out.println("Apply button pressed");
    }


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

                Set<MastersModel> compatibleMasters = newSelection.getCompatibleMasters();
                if (compatibleMasters != null && !compatibleMasters.isEmpty()) {
                    String mastersList = compatibleMasters.stream()
                            .map(MastersModel::getName)
                            .collect(Collectors.joining(", "));
                    compatibleMastersLabel.setText(mastersList);
                } else {
                    compatibleMastersLabel.setText("No compatible masters");
                }
            } else {
                noSelectionLabel.setVisible(true);
                detailsBox.setVisible(false);
            }
        });
    }
}

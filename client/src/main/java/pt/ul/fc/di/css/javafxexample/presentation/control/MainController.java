package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pt.ul.fc.di.css.javafxexample.presentation.model.ApplicationModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.DissertationTopicModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.StudentModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.ProfessorModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.MastersModel;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane rightPane;

  

    @FXML
    private Button dissertationTopicButton;

    @FXML
    private Button applicationsButton;


    @FXML
    public void initialize() {
        applicationsButton.setOnAction(event -> showApplicationList());
        dissertationTopicButton.setOnAction(event -> showDissertationTopicList());
    }


    private void showApplicationList() {
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/ListView.fxml", "/pt/ul/fc/di/css/javafxexample/presentation/view/ApplicationRightPanel.fxml", ApplicationModel.class);
    }

    private void showDissertationTopicList() {
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/ListView.fxml", "/pt/ul/fc/di/css/javafxexample/presentation/view/DissertationTopicRightPanel.fxml", DissertationTopicModel.class);
    }

    private <T> void loadListView(String listViewFxml, String rightPanelFxml, Class<T> modelClass) {
        try {
            // Load ListView
            FXMLLoader listViewLoader = new FXMLLoader(getClass().getResource(listViewFxml));
            Parent listViewRoot = listViewLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(listViewRoot);

            // Load Right Panel
            FXMLLoader rightPanelLoader = new FXMLLoader(getClass().getResource(rightPanelFxml));
            Parent rightPanelRoot = rightPanelLoader.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(rightPanelRoot);

            // Initialize Model and Controller
            DataModel<T> model = new DataModel<>();
            if(modelClass == DissertationTopicModel.class) {
                ((DataModel<DissertationTopicModel>) model).loadDissertationTopics();
            } else if(modelClass == ApplicationModel.class) {
                ((DataModel<ApplicationModel>) model).loadApplications();
            }

            ListController<T> listController = listViewLoader.getController();
            listController.initModel(model);

            // Optionally, you can initialize the right panel controller if needed
            // RightPanelController<T> rightPanelController = rightPanelLoader.getController();
            // rightPanelController.initModel(model);
            Object rightPanelController = rightPanelLoader.getController();
            if (rightPanelController instanceof DissertationTopicRightPanelController) {
                ((DissertationTopicRightPanelController) rightPanelController).initModel((ObjectProperty<DissertationTopicModel>) listController.selectedItemProperty());
            } else if (rightPanelController instanceof ApplicationRightPanelController) {
                ((ApplicationRightPanelController) rightPanelController).initModel((ObjectProperty<ApplicationModel>) listController.selectedItemProperty());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import pt.ul.fc.di.css.javafxexample.presentation.model.ApplicationModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.DissertationTopicModel;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane rightPane;

    @FXML
    private ProgressIndicator progressIndicator;

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
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/ApplicationRightPanel.fxml", ApplicationModel.class);
    }

    private void showDissertationTopicList() {
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/DissertationTopicRightPanel.fxml", DissertationTopicModel.class);
    }

    private <T> void loadListView(String rightPanelFxml, Class<T> modelClass) {
        showLoading();
        
        // Run the data loading in a separate thread
        new Thread(() -> {
            try {
                loadData(rightPanelFxml, modelClass);
            } finally {
                Platform.runLater(this::hideLoading);
            }
        }).start();
    }

    private <T> void loadData(String rightPanelFxml, Class<T> modelClass) {
        String listViewFxml = "/pt/ul/fc/di/css/javafxexample/presentation/view/ListView.fxml";

        try {
            // Simulate loading delay
            Thread.sleep(2000);

            // Load ListView
            FXMLLoader listViewLoader = new FXMLLoader(getClass().getResource(listViewFxml));
            Parent listViewRoot = listViewLoader.load();

            // Load Right Panel
            FXMLLoader rightPanelLoader = new FXMLLoader(getClass().getResource(rightPanelFxml));
            Parent rightPanelRoot = rightPanelLoader.load();

            // Initialize Model and Controller
            DataModel<T> model = new DataModel<>();
            if (modelClass == DissertationTopicModel.class) {
                ((DataModel<DissertationTopicModel>) model).loadDissertationTopics();
            } else if (modelClass == ApplicationModel.class) {
                ((DataModel<ApplicationModel>) model).loadApplications();
            }

            ListController<T> listController = listViewLoader.getController();
            Platform.runLater(() -> listController.initModel(model));

            // Optionally, you can initialize the right panel controller if needed
            Object rightPanelController = rightPanelLoader.getController();
            Platform.runLater(() -> {
                if (rightPanelController instanceof DissertationTopicRightPanelController) {
                    ((DissertationTopicRightPanelController) rightPanelController).initModel((ObjectProperty<DissertationTopicModel>) listController.selectedItemProperty());
                } else if (rightPanelController instanceof ApplicationRightPanelController) {
                    ((ApplicationRightPanelController) rightPanelController).initModel((ObjectProperty<ApplicationModel>) listController.selectedItemProperty());
                }
            });

            // Update the UI with both parts together
            Platform.runLater(() -> {
                contentPane.getChildren().clear();
                contentPane.getChildren().add(listViewRoot);
                rightPane.getChildren().clear();
                rightPane.getChildren().add(rightPanelRoot);
            });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showLoading() {
        progressIndicator.setVisible(true);
        contentPane.setDisable(true);
        rightPane.setDisable(true);
    }

    private void hideLoading() {
        progressIndicator.setVisible(false);
        contentPane.setDisable(false);
        rightPane.setDisable(false);
    }
}

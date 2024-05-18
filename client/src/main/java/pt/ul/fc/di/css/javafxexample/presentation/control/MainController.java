package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;
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
    private Button studentButton;

    @FXML
    private Button professorButton;

    @FXML
    private Button masterButton;

    @FXML
    public void initialize() {
        studentButton.setOnAction(event -> showStudentList());
        professorButton.setOnAction(event -> showProfessorList());
        masterButton.setOnAction(event -> showMasterList());
    }

    private void showStudentList() {
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/StudentListView.fxml", "/pt/ul/fc/di/css/javafxexample/presentation/view/StudentRightPanel.fxml", StudentModel.class);
    }

    private void showProfessorList() {
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/ProfessorListView.fxml", "/pt/ul/fc/di/css/javafxexample/presentation/view/ProfessorRightPanel.fxml", ProfessorModel.class);
    }

    private void showMasterList() {
        loadListView("/pt/ul/fc/di/css/javafxexample/presentation/view/MasterListView.fxml", "/pt/ul/fc/di/css/javafxexample/presentation/view/MasterRightPanel.fxml", MastersModel.class);
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
            if (modelClass == StudentModel.class) {
                ((DataModel<StudentModel>) model).loadStudents();
            } else if (modelClass == ProfessorModel.class) {
                ((DataModel<ProfessorModel>) model).loadProfessors();
            } else if (modelClass == MastersModel.class) {
                ((DataModel<MastersModel>) model).loadMasters();
            }

            ListController<T> listController = listViewLoader.getController();
            listController.initModel(model);

            // Optionally, you can initialize the right panel controller if needed
            // RightPanelController<T> rightPanelController = rightPanelLoader.getController();
            // rightPanelController.initModel(model);
            Object rightPanelController = rightPanelLoader.getController();
            if (rightPanelController instanceof StudentRightPanelController) {
                ((StudentRightPanelController) rightPanelController).initModel((ObjectProperty<StudentModel>) listController.selectedItemProperty());
            } else if (rightPanelController instanceof ProfessorRightPanelController) {
                ((ProfessorRightPanelController) rightPanelController).initModel((ObjectProperty<ProfessorModel>) listController.selectedItemProperty());
            } else if (rightPanelController instanceof MasterRightPanelController) {
                ((MasterRightPanelController) rightPanelController).initModel((ObjectProperty<MastersModel>) listController.selectedItemProperty());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

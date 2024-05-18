package pt.ul.fc.di.css.javafxexample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pt.ul.fc.di.css.javafxexample.presentation.control.ClientRestController;
import pt.ul.fc.di.css.javafxexample.presentation.control.ListController;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;


public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String prefix = "/pt/ul/fc/di/css/javafxexample/presentation/view/";

        BorderPane root = new BorderPane();
        FXMLLoader listLoader = new FXMLLoader(getClass().getResource(prefix + "MainView.fxml"));
        root.setCenter(listLoader.load());

        /*FXMLLoader editorLoader = new FXMLLoader(getClass().getResource(prefix + "editor.fxml"));
        root.setRight(editorLoader.load());
        EditorController editorController = editorLoader.getController();

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource(prefix + "menu.fxml"));
        root.setTop(menuLoader.load());
        MenuController menuController = menuLoader.getController();*/

        /*DataModel model = new DataModel();
        listController.initModel(model);
        editorController.initModel(model);
        menuController.initModel(model);*/
        

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Student Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

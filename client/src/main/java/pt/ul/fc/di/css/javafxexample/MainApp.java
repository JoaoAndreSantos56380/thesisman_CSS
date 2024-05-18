package pt.ul.fc.di.css.javafxexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ul.fc.di.css.javafxexample.presentation.control.LoginController;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginView();
    }

    public void showLoginView() throws Exception {
        String prefix = "/pt/ul/fc/di/css/javafxexample/presentation/view/";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "LoginView.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMainApp(this);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() throws Exception {
        String prefix = "/pt/ul/fc/di/css/javafxexample/presentation/view/";

        BorderPane root = new BorderPane();
        FXMLLoader listLoader = new FXMLLoader(getClass().getResource(prefix + "MainView.fxml"));
        root.setCenter(listLoader.load());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Student Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

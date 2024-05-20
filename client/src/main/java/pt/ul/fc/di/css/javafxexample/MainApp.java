package pt.ul.fc.di.css.javafxexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.ul.fc.di.css.javafxexample.presentation.control.LoginController;
import pt.ul.fc.di.css.javafxexample.presentation.control.MainController;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                primaryStage.requestFocus();
            }
        });
        showLoginView();
    }

    public void showLoginView() throws Exception {
        String prefix = "/pt/ul/fc/di/css/javafxexample/presentation/view/";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "LoginView.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMainApp(this);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void showMainView(String username) throws Exception {
        String prefix = "/pt/ul/fc/di/css/javafxexample/presentation/view/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "MainView.fxml"));
        BorderPane root = loader.load();

        // Wrap the BorderPane in a StackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(root);

        // Create the overlay Pane and add it to the StackPane
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        overlay.setVisible(false);
        stackPane.getChildren().add(overlay);

        // Pass the overlay Pane to the controller
        MainController controller = loader.getController();
        controller.setMainApp(this);
        controller.setOverlay(overlay);

        Scene scene = new Scene(stackPane, 800, 600);
        primaryStage.setTitle("Welcome " + username + "!");
        primaryStage.setScene(scene);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

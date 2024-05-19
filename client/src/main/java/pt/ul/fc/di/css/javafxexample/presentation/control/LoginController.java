package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import pt.ul.fc.di.css.javafxexample.MainApp;

import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    @FXML
    private StackPane contentPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessage;

    @FXML
    private ProgressIndicator progressIndicator;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() throws Exception {
        // Add a key listener to handle Enter key press
        usernameField.setOnKeyPressed(this::handleEnterKeyPress);
        passwordField.setOnKeyPressed(this::handleEnterKeyPress);
    }

    private void handleEnterKeyPress(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                performLogin();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setVisible(true);
        } else {
            errorMessage.setVisible(false);
            performLogin();
        }
    }

    private void performLogin() {
        // Show the loading screen
        showLoading();

        // Run the data loading in a separate thread
        new Thread(() -> {
            try {
                loginRequest();
                Platform.runLater(() -> {
                    try {
                        mainApp.showMainView(usernameField.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void loginRequest() {
        // Perform HTTP GET request to youtube.com
        try {
            URL url = new URL("https://www.youtube.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.disconnect();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoading() {
        progressIndicator.setVisible(true);
        contentPane.setDisable(true);
    }

    private void hideLoading() {
        progressIndicator.setVisible(false);
        contentPane.setDisable(false);
    }
}

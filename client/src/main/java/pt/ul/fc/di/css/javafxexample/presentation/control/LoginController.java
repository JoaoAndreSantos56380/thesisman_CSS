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

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    @FXML
    private void loginRequest() {
        String username = usernameField.getText();
        String urlString = "http://localhost:8080/api/login/" + username;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response to long
                long studentId = Long.parseLong(response.toString());

                // Print or use the student ID
                System.out.println("Student ID: " + studentId);
            } else {
                System.out.println("GET request failed");
            }
            connection.disconnect();
        } catch (Exception e) {
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

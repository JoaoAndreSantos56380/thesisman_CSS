package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pt.ul.fc.di.css.javafxexample.MainApp;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessage;
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
                mainApp.showMainView(usernameField.getText());
            }
        }
        catch(Exception e) {

        }
    }

    @FXML
    private void handleLogin(ActionEvent event) throws Exception{
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setVisible(true);
        } else {
            errorMessage.setVisible(false);

            mainApp.showMainView(username);

        }
    }

}

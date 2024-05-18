package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ul.fc.di.css.javafxexample.MainApp;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleLogin(ActionEvent event) throws Exception{
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Add your authentication logic here
        //if ("user".equals(username) && "password".equals(password)) {
        mainApp.showMainView();
        //} else {
            // Show error message (this is a simple example, so no error handling is implemented)
        //}
    }
}

package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ConfirmationPopupController {

    private Stage dialogStage;
    private boolean confirmed;

    @FXML
    private void handleCancelAction() {
        confirmed = false;
        dialogStage.close();
    }

    @FXML
    private void handleConfirmAction() {
        confirmed = true;
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

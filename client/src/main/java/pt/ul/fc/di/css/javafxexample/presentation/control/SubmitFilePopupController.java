package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SubmitFilePopupController {

    public enum DocumentType {
        PROPOSAL("Proposal"),
        FINAL_VERSION("Final Version");

        private final String displayName;

        DocumentType(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    private Stage dialogStage;
    private boolean confirmed;
    private long selectedId;
    
    @FXML
    private StackPane contentPane;

    @FXML
    private VBox contentBox;
    
    @FXML
    private ProgressIndicator loadingIndicator;
    
    @FXML
    private VBox confirmationBox;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @FXML
    private Label selectedDocumentLabel;

    @FXML
    private ComboBox<DocumentType> documentTypeComboBox;

    @FXML
    private void initialize() {
        documentTypeComboBox.getItems().setAll(DocumentType.values());
        documentTypeComboBox.getSelectionModel().select(DocumentType.PROPOSAL);
    }

    @FXML
    private void handleCancelAction() {
        confirmed = false;
        dialogStage.close();
    }

    @FXML
    private void handleConfirmAction() {
        confirmed = true;
        DocumentType selectedDocumentType = documentTypeComboBox.getSelectionModel().getSelectedItem();
        System.out.println("Confirm button pressed for application with id: " + selectedId + " and document type: " + selectedDocumentType);
        showLoadingIndicator(true);
        executorService.submit(this::makeGetRequest);
    }

    @FXML
    private void handleOkAction() {
        dialogStage.close();
    }

    private void makeGetRequest() {
        try {
            URL url = new URL("https://www.youtube.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Platform.runLater(() -> showConfirmationMessage(true));
            } else {
                Platform.runLater(() -> showConfirmationMessage(false));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Platform.runLater(() -> showConfirmationMessage(false));
        }
    }

    private void showLoadingIndicator(boolean show) {
        contentBox.setVisible(!show);
        loadingIndicator.setVisible(show);
    }

    private void showConfirmationMessage(boolean success) {
        loadingIndicator.setVisible(false);
        confirmationBox.setVisible(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setSelectedId(long selectedId) {
        this.selectedId = selectedId;
    }

    @FXML
    public void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(dialogStage);

        if (file != null) {
            String fileName = file.getName();
            System.out.println("Selected file: " + fileName);
            selectedDocumentLabel.setText(fileName);
            try {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                System.out.println("File content: " + fileContent.length + " bytes.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            selectedDocumentLabel.setText("None");
        }
    }
}

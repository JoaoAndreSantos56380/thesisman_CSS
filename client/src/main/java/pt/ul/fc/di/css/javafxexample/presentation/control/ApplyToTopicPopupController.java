package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ApplyToTopicPopupController {

    private Stage dialogStage;
    private boolean confirmed;
    private long selectedId; // Add this field to store the selected ID

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
    private Label result;


    @FXML
    private void handleCancelAction() {
        confirmed = false;
        dialogStage.close();
    }

    @FXML
    private void handleConfirmAction() {
        confirmed = true;
        System.out.println("Confirm button pressed for application with id: " + selectedId); // Print the selected ID
        showLoadingIndicator(true);
        executorService.submit(this::makeGetRequest);
    }

    @FXML
    private void handleOkAction() {
        dialogStage.close();
    }

    private void makeGetRequest() {
        System.err.println("Tring to");
        try {
            URL url = new URL("http://localhost:8080/api/createApplication/" +selectedId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

        // Create JSON object with studentId
            String jsonInputString = String.valueOf(MainControllerSingleton.user_id);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Request was successful
                Platform.runLater(() -> showConfirmationMessage(true));
            } else {
                // Request failed
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
        if(success) {
            result.setText("Application created successfully.");
        } else {
            result.setText("Failed to create application!");
        }
        loadingIndicator.setVisible(false);
        confirmationBox.setVisible(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    // Add this method to set the selected ID
    public void setSelectedId(long selectedId) {
        this.selectedId = selectedId;
    }
}

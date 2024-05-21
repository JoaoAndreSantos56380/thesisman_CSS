package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

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
    private String filePath;
    
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
        //alterar??
        executorService.submit(() -> {
            try {
                fileHandler();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleOkAction() {
        dialogStage.close();
    }

    //delete
    /*
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
    }*/

    private void showLoadingIndicator(boolean show) {
        contentBox.setVisible(!show);
        loadingIndicator.setVisible(show);
    }

    //is this right?
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
            String fileName = file.getAbsolutePath();
            filePath = fileName;
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

    public void fileHandler() throws IOException {
        if (filePath == null) {
            Platform.runLater(() -> showConfirmationMessage(false));
            return;
        }
    
        String boundary = Long.toHexString(System.currentTimeMillis());
        String CRLF = "\r\n";
        String charset = "UTF-8";
        File binaryFile = new File(filePath);
    
        URL url = new URL("http://localhost:8080/api/uploadProposal/" + selectedId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setDoOutput(true);
    
        try (OutputStream output = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true)) {
    
            // Enviar o arquivo binário
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
            writer.append("Content-Transfer-Encoding: binary").append(CRLF);
            writer.append(CRLF).flush();
            Files.copy(binaryFile.toPath(), output);
            output.flush();
            writer.append(CRLF).flush();
    
            // Finalizar a requisição
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }
    
        int responseCode = connection.getResponseCode();
        Platform.runLater(() -> showConfirmationMessage(responseCode == 200));
    }
    
}

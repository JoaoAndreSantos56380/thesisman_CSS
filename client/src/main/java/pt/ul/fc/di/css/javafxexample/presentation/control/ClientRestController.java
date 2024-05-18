package pt.ul.fc.di.css.javafxexample.presentation.control;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;

public class ClientRestController {

    private final Client client;

    public ClientRestController() {
        this.client = ClientBuilder.newClient();
    }

    public String sendGetRequest(String urlString) {
        WebTarget target = client.target(urlString);
        Response response = target.request().get();
        
        if (response.getStatus() == 200) {
            String entity = response.readEntity(String.class);
            response.close();
            return entity;
        } else {
            response.close();
            throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
        }
    }

    public static void main(String[] args) {
        ClientRestController clientRestController = new ClientRestController();
        String url = "http://example.com/api/resource"; // replace with your actual URL
        try {
            String response = clientRestController.sendGetRequest(url);
            System.out.println("Response from server: " + response);
        } catch (RuntimeException e) {
            System.err.println("Request failed: " + e.getMessage());
        }
    }
}

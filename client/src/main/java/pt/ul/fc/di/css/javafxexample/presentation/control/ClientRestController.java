package pt.ul.fc.di.css.javafxexample.presentation.control;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.Scanner;

public class ClientRestController {

    private final Client client;

    public ClientRestController() {
        this.client = ClientBuilder.newClient();
    }

    public void printUrlContent(String urlString) {
        WebTarget target = client.target(urlString);
        Response response = target.request().get();

        System.out.println("Content of URL: " + urlString);

        if (response.getStatus() == 200) {
            String content = response.readEntity(String.class);
            System.out.println(content);
        } else {
            System.err.println("Failed with HTTP error code : " + response.getStatus());
        }
    }

    public void testeEndPoints() {
        ClientRestController clientRestController = new ClientRestController();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the URL: ");
        String url = scanner.nextLine();

        clientRestController.printUrlContent(url);
    }
}

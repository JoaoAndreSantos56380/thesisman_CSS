package pt.ul.fc.di.css.javafxexample.presentation.model;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ul.fc.di.css.javafxexample.presentation.control.MainControllerSingleton;

public class DataModel<T> {

    private final ObservableList<T> itemsList = FXCollections.observableArrayList();
    private final ObjectProperty<T> currentItem = new SimpleObjectProperty<>(null);
    private final Client client = ClientBuilder.newClient();

    public ObservableList<T> getItemsList() {
        return itemsList;
    }

    public ObjectProperty<T> currentItemProperty() {
        return currentItem;
    }

    public T getCurrentItem() {
        return currentItem.get();
    }

    public void setCurrentItem(T item) {
        currentItem.set(item);
    }

    public void loadItems(T... items) {
        itemsList.setAll(items);
    }

    private String fetchUrlContent(String urlString) {
        WebTarget target = client.target(urlString);
        Response response = target.request().get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            System.err.println("Failed with HTTP error code : " + response.getStatus());
            return null;
        }
    }

    public void loadDissertationTopics() {
        String urlString = "http://localhost:8080/api/dissertationTopics/" + MainControllerSingleton.user_id;
        String content = fetchUrlContent(urlString);
        if (content != null) {
            try {
                List<DissertationTopicModel> dissertationTopics = APIParser.parseDissertationTopics(content);

                // Collect all items
                List<T> items = new ArrayList<>();
                if (!dissertationTopics.isEmpty()) {
                    for (DissertationTopicModel topic : dissertationTopics) {
                        items.add((T) topic);
                    }
                }

                // Process all items at once
                itemsList.setAll(items);  
              
            } catch (Exception e) {
                System.out.println("Failed to parse JSON!");
                //e.printStackTrace();
            }
        }
    }

    public void loadApplications() {
        String urlString = "http://localhost:8080/api/applications/" + MainControllerSingleton.user_id;
        String content = fetchUrlContent(urlString);
        if (content != null) {
            try {
                List<ApplicationModel> applications = APIParser.parseApplications(content);

                // Collect all items
                List<T> items = new ArrayList<>();
                if (!applications.isEmpty()) {
                    for (ApplicationModel application : applications) {
                        items.add((T) application);
                    }
                }

                // Process all items at once
                itemsList.setAll(items);             
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadThesisExecutions() {
        String urlString = "http://localhost:8080/api/thesisExecutions/" + MainControllerSingleton.user_id;
        String content = fetchUrlContent(urlString);
        if (content != null) {
            try {
                List<ThesisExecutionModel> executions = APIParser.parseThesisExecutions(content);

                // Collect all items
                List<T> items = new ArrayList<>();
                if (!executions.isEmpty()) {
                    for (ThesisExecutionModel execution : executions) {
                        items.add((T) execution);
                    }
                }

                // Process all items at once
                itemsList.setAll(items);  
              
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadThesisDefenses() {
        String urlString = "http://localhost:8080/api/thesisDefenses/" + MainControllerSingleton.user_id;
        String content = fetchUrlContent(urlString);
        if (content != null) {
            try {
                List<ThesisDefenseModel> defenses = APIParser.parseThesisDefenses(content);

                // Collect all items
                List<T> items = new ArrayList<>();
                if (!defenses.isEmpty()) {
                    for (ThesisDefenseModel defense : defenses) {
                        items.add((T) defense);
                    }
                }

                // Process all items at once
                itemsList.setAll(items);  
              
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

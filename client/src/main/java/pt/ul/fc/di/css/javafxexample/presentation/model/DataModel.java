package pt.ul.fc.di.css.javafxexample.presentation.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ul.fc.di.css.javafxexample.presentation.control.MainControllerSingleton;

public class DataModel<T> {

    private final ObservableList<T> itemsList = FXCollections.observableArrayList();
    private final ObjectProperty<T> currentItem = new SimpleObjectProperty<>(null);

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


    public void loadDissertationTopics() {
        try {
            URL url = new URL("http://localhost:8080/api/dissertationTopics/" + MainControllerSingleton.user_id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                List<DissertationTopicModel> dissertationTopics = APIParser.parseDissertationTopics(content.toString());

                // Collect all items
                List<T> items = new ArrayList<>();
                if (!dissertationTopics.isEmpty()) {
                    for (DissertationTopicModel topic : dissertationTopics) {
                        items.add((T) topic);
                    }
                }

                // Process all items at once
                itemsList.setAll(items);  
              
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Failed to parse JSON!");
            //e.printStackTrace();
        }
    }


    public void loadApplications() {
        try {
            URL url = new URL("http://localhost:8080/api/applications/" + MainControllerSingleton.user_id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Create a list to hold the ApplicationModel instances
                List<ApplicationModel> applications = APIParser.parseApplications(content.toString());


                // Collect all items
                List<T> items = new ArrayList<>();
                if (!applications.isEmpty()) {
                    for (ApplicationModel application : applications) {
                        items.add((T) application);
                    }
                }

                // Process all items at once
                itemsList.setAll(items);             
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void loadThesisExecutions() {
            try {
                URL url = new URL("http://localhost:8080/api/thesisExecutions/" + MainControllerSingleton.user_id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
    
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
    
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
    
                    // Create a list to hold the ApplicationModel instances
                    List<ThesisExecutionModel> executions = APIParser.parseThesisExecutions(content.toString());
    
    
                    // Collect all items
                    List<T> items = new ArrayList<>();
                    if (!executions.isEmpty()) {
                        for (ThesisExecutionModel execution : executions) {
                            items.add((T) execution);
                        }
                    }

                    // Process all items at once
                    itemsList.setAll(items);
                } else {
                    System.out.println("GET request failed. Response Code: " + responseCode);
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        
        public void loadThesisDefenses() {
            try {
                // Replace with the correct URL for thesis defenses
                URL url = new URL("http://localhost:8080/api/thesisDefenses/" + MainControllerSingleton.user_id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
    
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
    
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
    
                    // Create a list to hold the ThesisDefenseModel instances
                    List<ThesisDefenseModel> defenses = APIParser.parseThesisDefenses(content.toString());

                    // Collect all items
                    List<T> items = new ArrayList<>();
                    if (!defenses.isEmpty()) {
                        for (ThesisDefenseModel defense : defenses) {
                            items.add((T) defense);
                        }
                    }

                    // Process all items at once
                    itemsList.setAll(items);
                } else {
                    System.out.println("GET request failed. Response Code: " + responseCode);
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

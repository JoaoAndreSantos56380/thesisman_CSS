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

                // Parse the JSON response
                JsonArray topicsArray = JsonParser.parseString(content.toString()).getAsJsonArray();
                System.out.println(content.toString());
                // Print the number of objects in the array
                System.out.println("Number of Dissertation Topics: " + topicsArray.size());

                // List to hold the DissertationTopicModel instances
                List<DissertationTopicModel> dissertationTopics = new ArrayList<>();

                // Process each topic
                for (JsonElement topicElement : topicsArray) {
                    JsonObject topicObject = topicElement.getAsJsonObject();

                    long id = topicObject.get("id").getAsLong();
                    String title = topicObject.get("title").getAsString();
                    String description = topicObject.get("description").getAsString();
                    double salary = topicObject.get("salary").getAsDouble();

                    // Parse submitter
                    JsonObject submitterObject = topicObject.getAsJsonObject("submitter");
                    String submitterName = submitterObject.get("name").getAsString();
                    String submitterUsername = submitterObject.get("username").getAsString();
                    String submitterPassword = submitterObject.get("password").getAsString();

                    // Create submitter model
                    AppUserModel submitter = new AppUserModel(submitterUsername, submitterPassword, submitterName);

                    // Parse compatible masters
                    Set<MastersModel> compatibleMasters = new HashSet<>();
                    JsonArray mastersArray = topicObject.getAsJsonArray("compatibleMasters");
                    for (JsonElement masterElement : mastersArray) {
                        JsonObject masterObject = masterElement.getAsJsonObject();
                        String masterName = masterObject.get("name").getAsString();

                        // Parse coordinator
                        JsonObject coordinatorObject = masterObject.getAsJsonObject("coordinator");
                        String coordinatorName = coordinatorObject.get("name").getAsString();
                        String coordinatorUsername = coordinatorObject.get("username").getAsString();
                        String coordinatorPassword = coordinatorObject.get("password").getAsString();

                        // Create coordinator model
                        ProfessorModel coordinator = new ProfessorModel(coordinatorUsername, coordinatorPassword, coordinatorName);

                        // Create master model
                        MastersModel master = new MastersModel(masterName, coordinator);
                        compatibleMasters.add(master);
                    }

                    // Create dissertation topic model
                    DissertationTopicModel dissertationTopic = new DissertationTopicModel(title, description, salary, submitter, compatibleMasters);
                    dissertationTopic.setId(id);

                    // Add to the list
                    dissertationTopics.add(dissertationTopic);
                }

                // Call loadItems with the created dissertation topics
                if (!dissertationTopics.isEmpty()) {
                    // Convert list to array
                    DissertationTopicModel[] topicsArray2 = dissertationTopics.toArray(new DissertationTopicModel[0]);
                    // Cast to T[] and call loadItems
                    loadItems((T[]) topicsArray2);
                }
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

                // Parse the JSON response
                JsonArray applicationsArray = JsonParser.parseString(content.toString()).getAsJsonArray();
                System.out.println(content.toString());
                // Print the number of objects in the array
                System.out.println("Number of Applications: " + applicationsArray.size());

                // Create a list to hold the ApplicationModel instances
                List<ApplicationModel> applications = new ArrayList<>();

                // Process each application
                for (JsonElement applicationElement : applicationsArray) {
                    JsonObject applicationObject = applicationElement.getAsJsonObject();

                    long id = applicationObject.get("id").getAsLong();

                    // Parse student
                    JsonObject studentObject = applicationObject.getAsJsonObject("student");
                    String studentId = studentObject.get("id").getAsString();
                    String studentUsername = studentObject.get("username").getAsString();
                    String studentPassword = studentObject.get("password").getAsString();
                    String studentName = studentObject.get("name").getAsString();
                    int studentNumber = studentObject.get("studentNumber").getAsInt();
                    double averageGrade = studentObject.get("averageGrade").getAsDouble();

                    // Parse student's masters
                    JsonObject masterObject = studentObject.getAsJsonObject("masters");
                    long masterId = masterObject.get("id").getAsLong();
                    String masterName = masterObject.get("name").getAsString();

                    // Parse master's coordinator
                    JsonObject coordinatorObject = masterObject.getAsJsonObject("coordinator");
                    long coordinatorId = coordinatorObject.get("id").getAsLong();
                    String coordinatorUsername = coordinatorObject.get("username").getAsString();
                    String coordinatorPassword = coordinatorObject.get("password").getAsString();
                    String coordinatorName = coordinatorObject.get("name").getAsString();

                    // Create coordinator model
                    ProfessorModel coordinator = new ProfessorModel(coordinatorUsername, coordinatorPassword, coordinatorName);

                    // Create master's model
                    MastersModel master = new MastersModel(masterName, coordinator);

                    // Create student model
                    StudentModel student = new StudentModel(studentUsername, studentPassword, studentName, studentNumber, averageGrade, null);

                    // Parse topic
                    JsonObject topicObject = applicationObject.getAsJsonObject("topic");
                    long topicId = topicObject.get("id").getAsLong();
                    String topicTitle = topicObject.get("title").getAsString();
                    String topicDescription = topicObject.get("description").getAsString();
                    double topicSalary = topicObject.get("salary").getAsDouble();

                    // Parse submitter
                    JsonObject submitterObject = topicObject.getAsJsonObject("submitter");
                    long submitterId = submitterObject.get("id").getAsLong();
                    String submitterUsername = submitterObject.get("username").getAsString();
                    String submitterPassword = submitterObject.get("password").getAsString();
                    String submitterName = submitterObject.get("name").getAsString();

                    // Create submitter model
                    AppUserModel submitter = new AppUserModel(submitterUsername, submitterPassword, submitterName);

                    // Parse compatible masters
                    Set<MastersModel> compatibleMasters = new HashSet<>();
                    JsonArray mastersArray = topicObject.getAsJsonArray("compatibleMasters");
                    for (JsonElement masterElement : mastersArray) {
                        JsonObject compatibleMasterObject = masterElement.getAsJsonObject();
                        long compatibleMasterId = compatibleMasterObject.get("id").getAsLong();
                        String compatibleMasterName = compatibleMasterObject.get("name").getAsString();

                        // Parse coordinator of compatible master
                        JsonObject compatibleCoordinatorObject = compatibleMasterObject.getAsJsonObject("coordinator");
                        long compatibleCoordinatorId = compatibleCoordinatorObject.get("id").getAsLong();
                        String compatibleCoordinatorUsername = compatibleCoordinatorObject.get("username").getAsString();
                        String compatibleCoordinatorPassword = compatibleCoordinatorObject.get("password").getAsString();
                        String compatibleCoordinatorName = compatibleCoordinatorObject.get("name").getAsString();

                        // Create coordinator model
                        ProfessorModel compatibleCoordinator = new ProfessorModel(compatibleCoordinatorUsername, compatibleCoordinatorPassword, compatibleCoordinatorName);

                        // Create compatible master model
                        MastersModel compatibleMaster = new MastersModel(compatibleMasterName, compatibleCoordinator);
                        compatibleMasters.add(compatibleMaster);
                    }

                    // Create dissertation topic model
                    DissertationTopicModel topic = new DissertationTopicModel(topicTitle, topicDescription, topicSalary, submitter, compatibleMasters);
                    topic.setId(topicId);

                    // Create application model
                    ApplicationModel application = new ApplicationModel(student, topic);
                    application.setId(id);

                    // Add to the list
                    applications.add(application);
                }

                // Call loadItems with the created application models
                if (!applications.isEmpty()) {
                    for (ApplicationModel application : applications) {
                        loadItems((T) application);
                    }
                }
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
                URL url = new URL("https://www.youtube.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                connection.disconnect();
            } catch(Exception e) {
                e.printStackTrace();
            }
            // Mock data for students
            StudentModel student1 = new StudentModel("Alice Wonderland", "b", "c", 58);
            StudentModel student2 = new StudentModel("Bob Builder", "", "", 2);
            StudentModel student3 = new StudentModel("Charlie Chocolate", "", "", 4);
    
            ProfessorModel professor1 = new ProfessorModel("Prof. AI", "", "Prof. AI");
            ProfessorModel professor2 = new ProfessorModel("Prof. Cities","", "Prof. Cities");
            ProfessorModel professor3 = new ProfessorModel("Prof. Chocolate", "", "Prof. Chocolate");
            // Mock data for topics
            DissertationTopicModel topic1 = new DissertationTopicModel("AI in Wonderland", "aa", 10, professor1, null);
            DissertationTopicModel topic2 = new DissertationTopicModel("Smart Cities", "", 1, professor2, null);
            DissertationTopicModel topic3 = new DissertationTopicModel("Chocolate Automation", "", 1, professor3, null);
    
            // Mock data for ThesisExecution
            ThesisExecutionModel execution1 = new ThesisExecutionModel(student1, topic1, "2021/2022", professor1);
            ThesisExecutionModel execution2 = new ThesisExecutionModel(student2, topic2, "2022/2023", professor2);
            ThesisExecutionModel execution3 = new ThesisExecutionModel(student3, topic3, "2023/2024", professor3);
            execution1.setInternalAdvisor(professor1);
            execution2.setInternalAdvisor(professor2);
            execution3.setInternalAdvisor(professor3);
            // Load these executions into your application
            loadItems((T)execution1, (T)execution2, (T)execution3);
        }
    
        
        public void loadThesisDefenses() {
            try {
                URL url = new URL("https://www.youtube.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                connection.disconnect();
            } catch(Exception e) {
                e.printStackTrace();
            }
            // Mock data for professors
            StudentModel student1 = new StudentModel("Alice Wonderland", "b", "c", 58);
            StudentModel student2 = new StudentModel("Bob Builder", "", "", 2);
            StudentModel student3 = new StudentModel("Charlie Chocolate", "", "", 4);
    
            ProfessorModel professor1 = new ProfessorModel("Prof. AI", "", "Prof. AI");
            ProfessorModel professor2 = new ProfessorModel("Prof. Cities","", "Prof. Cities");
            ProfessorModel professor3 = new ProfessorModel("Prof. Chocolate", "", "Prof. Chocolate");
           
            // Mock data for compatible masters
            Set<MastersModel> mastersSet1 = new HashSet<>();
            mastersSet1.add(new MastersModel("Computer Science", professor1));
    
            Set<MastersModel> mastersSet2 = new HashSet<>();
            mastersSet2.add(new MastersModel("Data Science", professor2));
            mastersSet2.add(new MastersModel("Software Engineering", professor2));
    
            Set<MastersModel> mastersSet3 = new HashSet<>();
            mastersSet3.add(new MastersModel("Artificial Intelligence", professor3));
            mastersSet3.add(new MastersModel("Data Science", professor2));
            mastersSet3.add(new MastersModel("Software Engineering", professor3));
    
            // Mock data for topics
            DissertationTopicModel topic1 = new DissertationTopicModel(
                "AI in Wonderland",
                "Exploring AI concepts through Alice's adventures.",
                1500.0,
                new AppUserModel("alice.wonderland", "CheshireCat01", "Alice Wonderland"),
                mastersSet1
            );
    
            DissertationTopicModel topic2 = new DissertationTopicModel(
                "Building Smart Cities",
                "Technologies and methodologies for constructing smart cities.",
                1700.0,
                new AppUserModel("bob.builder", "FixItAll02", "Bob Builder"),
                mastersSet2
            );
    
            DissertationTopicModel topic3 = new DissertationTopicModel(
                "Chocolate Factory Automation",
                "Automating processes in chocolate production.",
                1400.0,
                new AppUserModel("charlie.chocolate", "GoldenTicket03", "Charlie Chocolate"),
                mastersSet3
            );
    
            // Mock data for ThesisExecution
            ThesisExecutionModel execution1 = new ThesisExecutionModel(student1, topic1, "2023", professor1);
            ThesisExecutionModel execution2 = new ThesisExecutionModel(student2, topic2, "2023", professor2);
            ThesisExecutionModel execution3 = new ThesisExecutionModel(student3, topic3, "2023", professor3);
    
            // Mock data for ThesisDefense
            ThesisDefenseModel defense1 = new ThesisDefenseModel(execution1, "Room 101", new Date());
            ThesisDefenseModel defense2 = new ThesisDefenseModel(execution2, "Room 102", new Date());
            ThesisDefenseModel defense3 = new ThesisDefenseModel(execution3, "Room 103", new Date());
    
            // Load these defenses into your application
            loadItems((T)defense1, (T)defense2, (T)defense3);
        }
}

package pt.ul.fc.di.css.javafxexample.presentation.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            URL url = new URL("https://www.youtube.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.disconnect();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Set<MastersModel> mastersSet1 = new HashSet<>();
        mastersSet1.add(new MastersModel("Computer Science", new ProfessorModel("Prof1", "hello", "mantorras")));

        Set<MastersModel> mastersSet2 = new HashSet<>();
        mastersSet2.add(new MastersModel("Data Science", new ProfessorModel("Prof2", "deus", "god")));
        mastersSet2.add(new MastersModel("Software Engineering", new ProfessorModel("Prof3", "css", "git")));

        Set<MastersModel> mastersSet3 = new HashSet<>();
        mastersSet3.add(new MastersModel("Artificial Intelligence", new ProfessorModel("Prof4", "ai", "ml")));
        mastersSet3.add(new MastersModel("Data Science", new ProfessorModel("Prof2", "deus", "god")));
        mastersSet3.add(new MastersModel("Software Engineering", new ProfessorModel("Prof3", "css", "git")));

        DissertationTopicModel topic1 = new DissertationTopicModel(
                "Artificial Intelligence in Wonderland",
                "Exploring AI concepts through Alice's adventures.",
                1500.0,
                new ProfessorModel("alice.wonderland", "CheshireCat01", "Alice Wonderland"),
                mastersSet1
        );

        DissertationTopicModel topic2 = new DissertationTopicModel(
                "Building Smart Cities",
                "Technologies and methodologies for constructing smart cities.",
                1700.0,
                new ProfessorModel("bob.builder", "FixItAll02", "Bob Builder"),
                mastersSet2
        );

        DissertationTopicModel topic3 = new DissertationTopicModel(
                "Chocolate Factory Automation",
                "Automating processes in chocolate production.",
                1400.0,
                new ProfessorModel("charlie.chocolate", "GoldenTicket03", "Charlie Chocolate"),
                mastersSet3
        );

        topic1.setId(1);
        topic2.setId(2);
        topic3.setId(3);

        // Add more DissertationTopicModel instances as needed

        // Load these topics into your application (for example, into a list or database)
        loadItems((T)topic1, (T)topic2, (T)topic3);
    }


    public void loadApplications() {

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
        StudentModel student1 = new StudentModel("1", "Alice Wonderland", "a", 58226, 1);
        StudentModel student2 = new StudentModel("2", "Bob Builder", "Bob", 58227, 1);
        StudentModel student3 = new StudentModel("3", "Charlie Chocolate", "Charlo", 58229, 1);

        Set<MastersModel> mastersSet1 = new HashSet<>();
        mastersSet1.add(new MastersModel("Computer Science", new ProfessorModel("Prof1", "hello", "mantorras")));

        Set<MastersModel> mastersSet2 = new HashSet<>();
        mastersSet2.add(new MastersModel("Data Science", new ProfessorModel("Prof2", "deus", "god")));
        mastersSet2.add(new MastersModel("Software Engineering", new ProfessorModel("Prof3", "css", "git")));

        Set<MastersModel> mastersSet3 = new HashSet<>();
        mastersSet3.add(new MastersModel("Artificial Intelligence", new ProfessorModel("Prof4", "ai", "ml")));
        mastersSet3.add(new MastersModel("Data Science", new ProfessorModel("Prof2", "deus", "god")));
        mastersSet3.add(new MastersModel("Software Engineering", new ProfessorModel("Prof3", "css", "git")));

        DissertationTopicModel topic1 = new DissertationTopicModel(
                "Artificial Intelligence in Wonderland",
                "Exploring AI concepts through Alice's adventures.",
                1500.0,
                new ProfessorModel("alice.wonderland", "CheshireCat01", "Alice Wonderland"),
                mastersSet1
        );

        DissertationTopicModel topic2 = new DissertationTopicModel(
                "Building Smart Cities",
                "Technologies and methodologies for constructing smart cities.",
                1700.0,
                new ProfessorModel("bob.builder", "FixItAll02", "Bob Builder"),
                mastersSet2
        );

        DissertationTopicModel topic3 = new DissertationTopicModel(
                "Chocolate Factory Automation",
                "Automating processes in chocolate production.",
                1400.0,
                new ProfessorModel("charlie.chocolate", "GoldenTicket03", "Charlie Chocolate"),
                mastersSet3
        );

       
    
            // Create mock applications
            ApplicationModel application1 = new ApplicationModel(student1, topic1);
            ApplicationModel application2 = new ApplicationModel(student2, topic2);
            ApplicationModel application3 = new ApplicationModel(student3, topic3);
            application1.setId(1);
            application2.setId(2);
            application3.setId(3);
            // Add more ApplicationModel instances as needed
            loadItems((T)application1, (T)application2, (T)application3);
        }

        public void loadThesisExecutions() {
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

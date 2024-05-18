package pt.ul.fc.di.css.javafxexample.presentation.model;

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

    public void loadStudents() {
        loadItems(
            (T)new StudentModel("alice.wonderland", "CheshireCat01", "Alice Wonderland", 1001, 15.5, new MastersModel("Computer Science", new ProfessorModel("Kandonga", "hello", "mantorras"))),
            (T)new StudentModel("bob.builder", "FixItAll02", "Bob Builder", 1002, 17.0, new MastersModel("Data Science", new ProfessorModel("Casemirao", "deus", "god"))),
            (T)new StudentModel("charlie.chocolate", "GoldenTicket03", "Charlie Chocolate", 1003, 14.0, new MastersModel("Software Engineering", new ProfessorModel("Al-çides", "css", "git")))
            // Add more StudentModel instances as needed
        );
    }

    public void loadDissertationTopics() {
        Set<MastersModel> mastersSet1 = new HashSet<>();
        mastersSet1.add(new MastersModel("Computer Science", new ProfessorModel("Kandonga", "hello", "mantorras")));

        Set<MastersModel> mastersSet2 = new HashSet<>();
        mastersSet2.add(new MastersModel("Data Science", new ProfessorModel("Casemirao", "deus", "god")));

        Set<MastersModel> mastersSet3 = new HashSet<>();
        mastersSet3.add(new MastersModel("Software Engineering", new ProfessorModel("Al-çides", "css", "git")));

        DissertationTopicModel topic1 = new DissertationTopicModel(
                "Artificial Intelligence in Wonderland",
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

        // Add more DissertationTopicModel instances as needed

        // Load these topics into your application (for example, into a list or database)
        loadItems((T)topic1, (T)topic2, (T)topic3);
    }

    public void loadProfessors() {
        loadItems(
            (T)new ProfessorModel("Kandonga", "hello", "mantorras"),
            (T)new ProfessorModel("Casemirao", "deus", "god"),
            (T)new ProfessorModel("Al-çides", "css", "git")
            // Add more ProfessorModel instances as needed
        );
    }

    public void loadMasters() {
        loadItems(
            (T)new MastersModel("Computer Science", new ProfessorModel("Kandonga", "hello", "mantorras")),
            (T)new MastersModel("Data Science", new ProfessorModel("Casemirao", "deus", "god")),
            (T)new MastersModel("Software Engineering", new ProfessorModel("Al-çides", "css", "git"))
            // Add more MastersModel instances as needed
        );
    }
}

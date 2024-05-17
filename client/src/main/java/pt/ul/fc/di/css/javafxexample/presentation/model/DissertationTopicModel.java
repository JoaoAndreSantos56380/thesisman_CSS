package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.*;

import java.util.Set;

public class DissertationTopicModel {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<DissertationTopicType> type = new SimpleObjectProperty<>();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final DoubleProperty salary = new SimpleDoubleProperty();
    private final ObjectProperty<AppUser> submitter = new SimpleObjectProperty<>();
    private final ObjectProperty<Set<Masters>> compatibleMasters = new SimpleObjectProperty<>();

    // Construtores
    public DissertationTopicModel() {
    }

    public DissertationTopicModel(String title, String description, double salary, AppUser submitter, Set<Masters> compatibleMasters) {
        setTitle(title);
        setDescription(description);
        setSalary(salary);
        setSubmitter(submitter);
        //setCompatibleMasters(compatibleMasters);
        determineType();
    }

    // Getters e setters para id
    public LongProperty idProperty() {
        return id;
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    // Getters e setters para type
    public ObjectProperty<DissertationTopicType> typeProperty() {
        return type;
    }

    public DissertationTopicType getType() {
        return type.get();
    }

    public void setType(DissertationTopicType type) {
        this.type.set(type);
    }

    // Getters e setters para title
    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    // Getters e setters para description
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    // Getters e setters para salary
    public DoubleProperty salaryProperty() {
        return salary;
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    // Getters e setters para submitter
    public ObjectProperty<AppUser> submitterProperty() {
        return submitter;
    }

    public AppUser getSubmitter() {
        return submitter.get();
    }

    public void setSubmitter(AppUser submitter) {
        this.submitter.set(submitter);
        determineType();
    }

    // Getters e setters para compatibleMasters
    public ObjectProperty<Set<Masters>> compatibleMastersProperty() {
        return compatibleMasters;
    }

    public Set<Masters> getCompatibleMasters() {
        return compatibleMasters.get();
    }

    public void setCompatibleMasters(Set<Masters> compatibleMasters) {
        this.compatibleMasters.set(compatibleMasters);
    }

    private void determineType() {
        if (submitter != null && submitter.get() != null) {
            if (submitter.get().getClass() == Professor.class) {
                setType(DissertationTopicType.DISSERTATION);
            } else {
                setType(DissertationTopicType.PROJECT);
            }
        }
    }

    @Override
    public String toString() {
        return "DissertationTopic id: " + getId() + ", title:" + getTitle() + ", description:" + getDescription();
    }
}

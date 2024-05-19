package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.*;

import java.util.Date;

public class ThesisDefenseModel {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<ThesisExecutionModel> thesisExecution = new SimpleObjectProperty<>();
    private final IntegerProperty grade = new SimpleIntegerProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final ObjectProperty<Date> time = new SimpleObjectProperty<>();

    // Construtores
    public ThesisDefenseModel() {
    }

    public ThesisDefenseModel(ThesisExecutionModel thesisExecution, String location, Date time) {
        setThesisExecution(thesisExecution);
        setLocation(location);
        setTime(time);
        setGrade(0); // Default grade to 0
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

    // Getters e setters para thesisExecution
    public ObjectProperty<ThesisExecutionModel> thesisExecutionProperty() {
        return thesisExecution;
    }

    public ThesisExecutionModel getThesisExecution() {
        return thesisExecution.get();
    }

    public void setThesisExecution(ThesisExecutionModel thesisExecution) {
        this.thesisExecution.set(thesisExecution);
    }

    // Getters e setters para grade
    public IntegerProperty gradeProperty() {
        return grade;
    }

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    // Getters e setters para location
    public StringProperty locationProperty() {
        return location;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    // Getters e setters para time
    public ObjectProperty<Date> timeProperty() {
        return time;
    }

    public Date getTime() {
        return time.get();
    }

    public void setTime(Date time) {
        this.time.set(time);
    }

    @Override
    public String toString() {
        return "ThesisDefense id: " + getId() + ", thesisExecution: " + getThesisExecution() +
               ", location: " + getLocation() + ", time: " + getTime() + ", grade: " + getGrade();
    }
}

package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.*;
import java.util.Date;

public class ThesisDefenseModel {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<ThesisExecutionModel> thesisExecution = new SimpleObjectProperty<>();
    private final IntegerProperty grade = new SimpleIntegerProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final ObjectProperty<Date> time = new SimpleObjectProperty<>();

    public ThesisDefenseModel() {
        // Default constructor
    }

    public ThesisDefenseModel(ThesisExecutionModel thesisExecution, String location, Date time) {
        this.thesisExecution.set(thesisExecution);
        this.location.set(location);
        this.time.set(time);
        this.grade.set(0); // Default grade
    }

    public LongProperty idProperty() {
        return id;
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public ObjectProperty<ThesisExecutionModel> thesisExecutionProperty() {
        return thesisExecution;
    }

    public ThesisExecutionModel getThesisExecution() {
        return thesisExecution.get();
    }

    public void setThesisExecution(ThesisExecutionModel thesisExecution) {
        this.thesisExecution.set(thesisExecution);
    }

    public IntegerProperty gradeProperty() {
        return grade;
    }

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public StringProperty locationProperty() {
        return location;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

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
        return "ThesisDefenseModel{" +
                "id=" + id.get() +
                ", thesisExecution=" + thesisExecution.get() +
                ", grade=" + grade.get() +
                ", location='" + location.get() + '\'' +
                ", time=" + time.get() +
                '}';
    }
}

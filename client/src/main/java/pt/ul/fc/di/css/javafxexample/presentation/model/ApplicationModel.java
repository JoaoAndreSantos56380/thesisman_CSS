package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ApplicationModel {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<StudentModel> student = new SimpleObjectProperty<>();
    private final ObjectProperty<DissertationTopicModel> topic = new SimpleObjectProperty<>();

    public ApplicationModel(StudentModel student, DissertationTopicModel topic) {
        setStudent(student);
        setTopic(topic);
    }

    public ApplicationModel(long id, StudentModel student, DissertationTopicModel topic) {
        setId(id);
        setStudent(student);
        setTopic(topic);
    }

    public LongProperty idProperty() {
        return id;
    }

    public ObjectProperty<StudentModel> studentProperty() {
        return student;
    }

    public ObjectProperty<DissertationTopicModel> topicProperty() {
        return topic;
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public StudentModel getStudent() {
        return student.get();
    }

    public void setStudent(StudentModel student) {
        this.student.set(student);
    }

    public DissertationTopicModel getTopic() {
        return topic.get();
    }

    public void setTopic(DissertationTopicModel topic) {
        this.topic.set(topic);
    }

    @Override
    public String toString() {
        return "ApplicationModel[" +
                "id=" + getId() +
                ", student=" + getStudent() +
                ", topic=" + getTopic() +
                ']';
    }
}

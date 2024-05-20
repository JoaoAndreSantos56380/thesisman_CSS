package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.*;

public class ThesisExecutionModel {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<StudentModel> student = new SimpleObjectProperty<>();
    private final ObjectProperty<DissertationTopicModel> topic = new SimpleObjectProperty<>();
    private final ObjectProperty<ProfessorModel> internalAdvisor = new SimpleObjectProperty<>();
    private final StringProperty yearOfExecution = new SimpleStringProperty();
    private final IntegerProperty finalGrade = new SimpleIntegerProperty();

    public ThesisExecutionModel() {
        // Default constructor
    }

    public ThesisExecutionModel(StudentModel student, DissertationTopicModel topic, String yearOfExecution, ProfessorModel internalAdvisor) {
        this.student.set(student);
        this.topic.set(topic);
        this.yearOfExecution.set(yearOfExecution);
        this.internalAdvisor.set(internalAdvisor);
        this.finalGrade.set(0); // Default final grade
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

    public ObjectProperty<StudentModel> studentProperty() {
        return student;
    }

    public StudentModel getStudent() {
        return student.get();
    }

    public void setStudent(StudentModel student) {
        this.student.set(student);
    }

    public ObjectProperty<DissertationTopicModel> topicProperty() {
        return topic;
    }

    public DissertationTopicModel getTopic() {
        return topic.get();
    }

    public void setTopic(DissertationTopicModel topic) {
        this.topic.set(topic);
    }

    public ObjectProperty<ProfessorModel> internalAdvisorProperty() {
        return internalAdvisor;
    }

    public ProfessorModel getInternalAdvisor() {
        return internalAdvisor.get();
    }

    public void setInternalAdvisor(ProfessorModel internalAdvisor) {
        this.internalAdvisor.set(internalAdvisor);
    }

    public StringProperty yearOfExecutionProperty() {
        return yearOfExecution;
    }

    public String getYearOfExecution() {
        return yearOfExecution.get();
    }

    public void setYearOfExecution(String yearOfExecution) {
        this.yearOfExecution.set(yearOfExecution);
    }

    public IntegerProperty finalGradeProperty() {
        return finalGrade;
    }

    public int getFinalGrade() {
        return finalGrade.get();
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade.set(finalGrade);
    }

    @Override
    public String toString() {
        return yearOfExecution.get() + " - " + topic.get().getTitle();
        /*return "ThesisExecutionModel{" +
                "id=" + id.get() +
                ", student=" + student.get() +
                ", topic=" + topic.get() +
                ", internalAdvisor=" + internalAdvisor.get() +
                ", yearOfExecution='" + yearOfExecution.get() + '\'' +
                ", finalGrade=" + finalGrade.get() +
                '}';*/
    }
}

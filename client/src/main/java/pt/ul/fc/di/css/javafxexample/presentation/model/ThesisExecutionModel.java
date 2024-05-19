package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.*;

public class ThesisExecutionModel {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<StudentModel> student = new SimpleObjectProperty<>();
    private final ObjectProperty<DissertationTopicModel> topic = new SimpleObjectProperty<>();
    private final ObjectProperty<ProfessorModel> internalAdvisor = new SimpleObjectProperty<>();
    private final StringProperty yearOfExecution = new SimpleStringProperty();
    private final IntegerProperty finalGrade = new SimpleIntegerProperty();

    // Construtores
    public ThesisExecutionModel() {
    }

    public ThesisExecutionModel(StudentModel student, DissertationTopicModel topic, String yearOfExecution) {
        setStudent(student);
        setTopic(topic);
        setYearOfExecution(yearOfExecution);
        setInternalAdvisor((ProfessorModel) topic.getSubmitter());
    }

    public ThesisExecutionModel(StudentModel student, DissertationTopicModel topic, String yearOfExecution, ProfessorModel internalAdvisor) {
        setStudent(student);
        setTopic(topic);
        setYearOfExecution(yearOfExecution);
        setInternalAdvisor(internalAdvisor);
    }

    //MOCKKK
    public ThesisExecutionModel(StudentModel student, String yearOfExecution) {
        setStudent(student);
        setYearOfExecution(yearOfExecution);
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

    // Getters e setters para student
    public ObjectProperty<StudentModel> studentProperty() {
        return student;
    }

    public StudentModel getStudent() {
        return student.get();
    }

    public void setStudent(StudentModel student) {
        this.student.set(student);
    }

    // Getters e setters para topic
    public ObjectProperty<DissertationTopicModel> topicProperty() {
        return topic;
    }

    public DissertationTopicModel getTopic() {
        return topic.get();
    }

    public void setTopic(DissertationTopicModel topic) {
        this.topic.set(topic);
    }

    // Getters e setters para internalAdvisor
    public ObjectProperty<ProfessorModel> internalAdvisorProperty() {
        return internalAdvisor;
    }

    public ProfessorModel getInternalAdvisor() {
        return internalAdvisor.get();
    }

    public void setInternalAdvisor(ProfessorModel internalAdvisor) {
        this.internalAdvisor.set(internalAdvisor);
    }

    // Getters e setters para yearOfExecution
    public StringProperty yearOfExecutionProperty() {
        return yearOfExecution;
    }

    public String getYearOfExecution() {
        return yearOfExecution.get();
    }

    public void setYearOfExecution(String yearOfExecution) {
        this.yearOfExecution.set(yearOfExecution);
    }

    // Getters e setters para finalGrade
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
        return "ThesisExecution id: " + getId() + ", student: " + getStudent() +
               ", topic: " + getTopic() + ", internalAdvisor: " + getInternalAdvisor() +
               ", yearOfExecution: " + getYearOfExecution() + ", finalGrade: " + getFinalGrade();
    }
}

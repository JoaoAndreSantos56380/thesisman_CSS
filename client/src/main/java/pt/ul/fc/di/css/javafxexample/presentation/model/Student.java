package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends AppUser {
    private final IntegerProperty studentNumber = new SimpleIntegerProperty();
    private final DoubleProperty averageGrade = new SimpleDoubleProperty();
    private final ObjectProperty<Masters> master = new SimpleObjectProperty<>();

    // Construtor com averageGrade
    public Student(String username, String password, String name, int studentNumber, double averageGrade, Masters master) {
        super(username, password, name);
        setStudentNumber(studentNumber);
        setAverageGrade(averageGrade);
        setMaster(master);
    }

    // Construtor com averageGrade
    public Student(String username, String password, String name, int studentNumber, double averageGrade) {
        super(username, password, name);
        setStudentNumber(studentNumber);
        setAverageGrade(averageGrade);
    }

    // Construtor sem averageGrade
    public Student(String username, String password, String name, int studentNumber, Masters master) {
        this(username, password, name, studentNumber, 0.0, master);
    }

    // Construtor sem Masters
    public Student(String username, String password, String name, int studentNumber) {
        this(username, password, name, studentNumber, 0.0);
    }

    // Getters e setters para studentNumber
    public IntegerProperty studentNumberProperty() {
        return studentNumber;
    }

    public int getStudentNumber() {
        return studentNumber.get();
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber.set(studentNumber);
    }

    // Getters e setters para averageGrade
    public DoubleProperty averageGradeProperty() {
        return averageGrade;
    }

    public double getAverageGrade() {
        return averageGrade.get();
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade.set(averageGrade);
    }

    // Getters e setters para master
    public ObjectProperty<Masters> masterProperty() {
        return master;
    }

    public Masters getMaster() {
        return master.get();
    }

    public void setMaster(Masters master) {
        this.master.set(master);
    }

    @Override
    public String toString() {
        return "Name " + getUsername()
         + ", average grade: " + getAverageGrade() + ", Masters: " + getMaster();
    }
}

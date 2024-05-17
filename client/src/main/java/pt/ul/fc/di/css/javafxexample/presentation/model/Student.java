package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty studentNumber = new SimpleIntegerProperty();
    private final DoubleProperty averageGrade = new SimpleDoubleProperty();
    private final ObjectProperty<Masters> master = new SimpleObjectProperty<>();

    // Construtor com averageGrade
    public Student(String username, String password, String name, int studentNumber, double averageGrade, Masters master) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setStudentNumber(studentNumber);
        setAverageGrade(averageGrade);
        setMaster(master);
    }

    // Construtor com averageGrade
    public Student(String username, String password, String name, int studentNumber, double averageGrade) {
        setUsername(username);
        setPassword(password);
        setName(name);
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

    // Construtor vazio
    public Student() {
    }

    // Getters e setters para username
    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    // Getters e setters para password
    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    // Getters e setters para name
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
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
        return "Student Number " + getStudentNumber() + ", name " + getName() + 
               ", Masters: " + getMaster();
    }
}

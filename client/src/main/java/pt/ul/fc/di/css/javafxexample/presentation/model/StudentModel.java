package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import pt.ul.fc.css.example.demo.entities.AppUser;
//import pt.ul.fc.css.example.demo.entities.Student;


public class StudentModel extends AppUserModel {
    private final IntegerProperty studentNumber = new SimpleIntegerProperty();
    private final DoubleProperty averageGrade = new SimpleDoubleProperty();
    private final ObjectProperty<MastersModel> master = new SimpleObjectProperty<>();

    // Construtor com averageGrade
    public StudentModel(String username, String password, String name, int studentNumber, double averageGrade, MastersModel master) {
        super(username, password, name);
        //Student student = new Student(username, password, name, studentNumber, averageGrade, master);
    
        setStudentNumber(studentNumber);
        setAverageGrade(averageGrade);
        setMaster(master);
    }

    // Construtor com averageGrade
    public StudentModel(String username, String password, String name, int studentNumber, double averageGrade) {
        super(username, password, name);
        setStudentNumber(studentNumber);
        setAverageGrade(averageGrade);
    }

    // Construtor sem averageGrade
    public StudentModel(String username, String password, String name, int studentNumber, MastersModel master) {
        this(username, password, name, studentNumber, 0.0, master);
    }

    // Construtor sem Masters
    public StudentModel(String username, String password, String name, int studentNumber) {
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
    public ObjectProperty<MastersModel> masterProperty() {
        return master;
    }

    public MastersModel getMaster() {
        return master.get();
    }

    public void setMaster(MastersModel master) {
        this.master.set(master);
    }

    @Override
    public String toString() {
        return "Name " + getUsername()
         + ", average grade: " + getAverageGrade() + ", Masters: " + getMaster();
    }
}

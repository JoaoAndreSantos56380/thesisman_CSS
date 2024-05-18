package pt.ul.fc.di.css.javafxexample.presentation.control;

import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.StudentModel;

public class EditorController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField studentNumberField;
    @FXML
    private TextField averageGradeField;
    @FXML
    private TextField masterField;

    private DataModel model;

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model;
        model.currentStudentProperty().addListener((obs, oldStudent, newStudent) -> {
            if (oldStudent != null) {
                unbindStudent(oldStudent);
            }
            if (newStudent == null) {
                clearFields();
            } else {
                bindStudent(newStudent);
            }
        });
    }

    private void bindStudent(StudentModel student) {
        usernameField.textProperty().bindBidirectional(student.usernameProperty());
        passwordField.textProperty().bindBidirectional(student.passwordProperty());
        nameField.textProperty().bindBidirectional(student.nameProperty());
        studentNumberField.textProperty().bindBidirectional(student.studentNumberProperty(), new NumberStringConverter(new Locale("pt", "PT")));
        averageGradeField.textProperty().bindBidirectional(student.averageGradeProperty(), new NumberStringConverter(new Locale("pt", "PT")));
        // masterField should be bound to master's name property or similar if available
        if (student.getMaster() != null) {
            masterField.setText(student.getMaster().getName());
        } else {
            masterField.setText("");
        }
    }

    private void unbindStudent(StudentModel student) {
        usernameField.textProperty().unbindBidirectional(student.usernameProperty());
        passwordField.textProperty().unbindBidirectional(student.passwordProperty());
        nameField.textProperty().unbindBidirectional(student.nameProperty());
        studentNumberField.textProperty().unbindBidirectional(student.studentNumberProperty());
        averageGradeField.textProperty().unbindBidirectional(student.averageGradeProperty());
        masterField.setText("");
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        nameField.setText("");
        studentNumberField.setText("");
        averageGradeField.setText("");
        masterField.setText("");
    }
}

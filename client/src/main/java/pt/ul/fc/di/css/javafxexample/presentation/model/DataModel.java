package pt.ul.fc.di.css.javafxexample.presentation.model;

import java.io.File;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

	/* in this way personList also reports 
	 * mutations of the elements in it by using the given extractor. 
	 * Observable objects returned by extractor (applied to each list element) are listened 
	 * for changes and transformed into "update" change of ListChangeListener.	  
	 * since the phone is not visible, changes in the phone do not need to be propagated	 
	 */
	private final ObservableList<Student> personList =	 
			FXCollections.observableArrayList(student -> 
			new Observable[] {student.usernameProperty(), student.studentNumberProperty()});

	public ObservableList<Student> getStudentList() {
		return personList;
	}

	private final ObjectProperty<Student> currentStudent = new SimpleObjectProperty<>(null);

	
	public ObjectProperty<Student> currentStudentProperty() {
		return currentStudent;
	}

	public final Student getCurrentStudent() {
		return currentStudentProperty().get();
	}

	public final void setCurrentStudent(Student student) {
		currentStudentProperty().set(student);
	}

	public void loadData() {
		
		Masters master1 = new Masters("Computer Science", "crisss");
        Masters master2 = new Masters("Data Science", "jose manel");
        Masters master3 = new Masters("Software Engineering", "castelo preto");

        personList.setAll(
            new Student("jose.silva", "password123", "Jose Silva", 1001, 15.5, master1),
            new Student("isabel.ramos", "password456", "Isabel Ramos", 1002, 17.0, master2),
            new Student("eloi.matos", "password789", "Eloi Matos", 1003, 14.0, master1),
            new Student("ema.antunes", "password012", "Ema Antunes", 1004, 18.5, master3),
            new Student("paulo.guerra", "password345", "Paulo Guerra", 1005, 16.0, master1),
            new Student("ana.santos", "password678", "Ana Santos", 1006, 19.0, master2),
            new Student("maria.oliveira", "password901", "Maria Oliveira", 1007, 13.5, master3),
            new Student("rui.rodrigues", "password234", "Rui Rodrigues", 1008, 16.5, master1),
            new Student("carlos.lima", "password567", "Carlos Lima", 1009, 15.0, master2),
            new Student("sofia.pereira", "password890", "Sofia Pereira", 1010, 17.5, master3)
        );
	}

	public void saveData(File file) { }
}
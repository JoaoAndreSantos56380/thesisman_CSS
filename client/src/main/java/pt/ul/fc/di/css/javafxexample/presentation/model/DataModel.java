package pt.ul.fc.di.css.javafxexample.presentation.model;

import java.io.File;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

	/* in this way studentsList also reports 
	 * mutations of the elements in it by using the given extractor. 
	 * Observable objects returned by extractor (applied to each list element) are listened 
	 * for changes and transformed into "update" change of ListChangeListener.	  
	 * since the phone is not visible, changes in the phone do not need to be propagated	 
	 */
	private final ObservableList<Student> studentsList =	 
			FXCollections.observableArrayList(student -> 
			new Observable[] {student.usernameProperty(), student.studentNumberProperty()});

	private final ObservableList<Professor> profsList =	 
			FXCollections.observableArrayList(professor -> 
			new Observable[] {professor.usernameProperty(), professor.nameProperty()});

	private final ObservableList<Masters> mastersList =	 
			FXCollections.observableArrayList(master -> 
			new Observable[] {master.nameProperty(), master.coordenatorProperty()});


	public ObservableList getList() {
		if (!this.mastersList.isEmpty()) {
			return mastersList;
		}
		if (!this.profsList.isEmpty()) {
			return mastersList;
		}
		return studentsList;
	}

	public ObservableList<Professor> getProfessorsList() {
		return profsList;
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
		
		System.out.println("alooo");
		this.studentsList.setAll();
	}

	public void saveData(File file) { }


	public void loadProfessors() {

		Professor prof1 = new Professor("Kandonga", "hello", "mantorras");
		Professor prof2 = new Professor("Casemirao", "deus", "god");
		Professor prof3 = new Professor("Al-çides", "css", "git");

		//this.profsList.setAll(prof1, prof2, prof3);
		this.profsList.setAll(prof1, prof2, prof3);
	}

	public void loadStudents() {

		Professor prof1 = new Professor("Kandonga", "hello", "mantorras");
		Professor prof2 = new Professor("Casemirao", "deus", "god");
		Professor prof3 = new Professor("Al-çides", "css", "git");

		Masters master1 = new Masters("Computer Science", prof1);
        Masters master2 = new Masters("Data Science", prof2);
        Masters master3 = new Masters("Software Engineering", prof3);

        studentsList.setAll(
			new Student("alice.wonderland", "CheshireCat01", "Alice Wonderland", 1001, 15.5, master1),
			new Student("bob.builder", "FixItAll02", "Bob Builder", 1002, 17.0, master2),
			new Student("charlie.chocolate", "GoldenTicket03", "Charlie Chocolate", 1003, 14.0, master1),
			new Student("dora.explorer", "MapQuest04", "Dora Explorer", 1004, 18.5, master3),
			new Student("elmo.sesame", "TickleMe05", "Elmo Sesame", 1005, 16.0, master1),
			new Student("frodo.ring", "ShireHobbit06", "Frodo Ring", 1006, 19.0, master2),
			new Student("gandalf.grey", "YouShallPass07", "Gandalf Grey", 1007, 13.5, master3),
			new Student("harry.potter", "Expelliarmus08", "Harry Potter", 1008, 16.5, master1),
			new Student("irene.adler", "Sherlocked09", "Irene Adler", 1009, 15.0, master2),
			new Student("jack.sparrow", "PirateLife10", "Jack Sparrow", 1010, 17.5, master3),
			new Student("katniss.everdeen", "Mockingjay11", "Katniss Everdeen", 1011, 18.0, master1),
			new Student("legolas.greenleaf", "ElvenArcher12", "Legolas Greenleaf", 1012, 16.5, master2),
			new Student("merlin.wizard", "MagicStaff13", "Merlin Wizard", 1013, 14.5, master3),
			new Student("nemo.clownfish", "JustKeepSwimming14", "Nemo Clownfish", 1014, 17.0, master1),
			new Student("oliver.twist", "PleaseSir15", "Oliver Twist", 1015, 15.5, master2),
			new Student("peter.pan", "NeverGrowUp16", "Peter Pan", 1016, 19.0, master3),
			new Student("quentin.quirrell", "TurbansRock17", "Quentin Quirrell", 1017, 13.0, master1),
			new Student("rapunzel.tower", "LetDownYourHair18", "Rapunzel Tower", 1018, 18.5, master2),
			new Student("sherlock.holmes", "221BakerStreet19", "Sherlock Holmes", 1019, 17.5, master3),
			new Student("tony.stark", "IronMan20", "Tony Stark", 1020, 16.0, master1)
		);
	}

	public void loadMasters() {

		Professor prof1 = new Professor("Kandonga", "hello", "mantorras");
		Professor prof2 = new Professor("Casemirao", "deus", "god");
		Professor prof3 = new Professor("Al-çides", "css", "git");

		Masters master1 = new Masters("Computer Science", prof1);
        Masters master2 = new Masters("Data Science", prof2);
        Masters master3 = new Masters("Software Engineering", prof3);

        mastersList.setAll(master1, master2, master3);
	}
}
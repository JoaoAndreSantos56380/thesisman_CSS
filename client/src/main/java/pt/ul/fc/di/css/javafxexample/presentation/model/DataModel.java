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
	private final ObservableList<StudentModel> studentsList =	 
			FXCollections.observableArrayList(student -> 
			new Observable[] {student.usernameProperty(), student.studentNumberProperty()});

	private final ObservableList<ProfessorModel> profsList =	 
			FXCollections.observableArrayList(professor -> 
			new Observable[] {professor.usernameProperty(), professor.nameProperty()});

	private final ObservableList<MastersModel> mastersList =	 
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

	public ObservableList<ProfessorModel> getProfessorsList() {
		return profsList;
	}

	private final ObjectProperty<StudentModel> currentStudent = new SimpleObjectProperty<>(null);

	
	public ObjectProperty<StudentModel> currentStudentProperty() {
		return currentStudent;
	}

	public final StudentModel getCurrentStudent() {
		return currentStudentProperty().get();
	}

	public final void setCurrentStudent(StudentModel student) {
		currentStudentProperty().set(student);
	}

	public void loadData() {
		
		System.out.println("alooo");
		this.studentsList.setAll();
	}

	public void saveData(File file) { }


	public void loadProfessors() {

		ProfessorModel prof1 = new ProfessorModel("Kandonga", "hello", "mantorras");
		ProfessorModel prof2 = new ProfessorModel("Casemirao", "deus", "god");
		ProfessorModel prof3 = new ProfessorModel("Al-çides", "css", "git");

		//this.profsList.setAll(prof1, prof2, prof3);
		this.profsList.setAll(prof1, prof2, prof3);
	}

	public void loadStudents() {

		ProfessorModel prof1 = new ProfessorModel("Kandonga", "hello", "mantorras");
		ProfessorModel prof2 = new ProfessorModel("Casemirao", "deus", "god");
		ProfessorModel prof3 = new ProfessorModel("Al-çides", "css", "git");

		MastersModel master1 = new MastersModel("Computer Science", prof1);
        MastersModel master2 = new MastersModel("Data Science", prof2);
        MastersModel master3 = new MastersModel("Software Engineering", prof3);

        studentsList.setAll(
			new StudentModel("alice.wonderland", "CheshireCat01", "Alice Wonderland", 1001, 15.5, master1),
			new StudentModel("bob.builder", "FixItAll02", "Bob Builder", 1002, 17.0, master2),
			new StudentModel("charlie.chocolate", "GoldenTicket03", "Charlie Chocolate", 1003, 14.0, master1),
			new StudentModel("dora.explorer", "MapQuest04", "Dora Explorer", 1004, 18.5, master3),
			new StudentModel("elmo.sesame", "TickleMe05", "Elmo Sesame", 1005, 16.0, master1),
			new StudentModel("frodo.ring", "ShireHobbit06", "Frodo Ring", 1006, 19.0, master2),
			new StudentModel("gandalf.grey", "YouShallPass07", "Gandalf Grey", 1007, 13.5, master3),
			new StudentModel("harry.potter", "Expelliarmus08", "Harry Potter", 1008, 16.5, master1),
			new StudentModel("irene.adler", "Sherlocked09", "Irene Adler", 1009, 15.0, master2),
			new StudentModel("jack.sparrow", "PirateLife10", "Jack Sparrow", 1010, 17.5, master3),
			new StudentModel("katniss.everdeen", "Mockingjay11", "Katniss Everdeen", 1011, 18.0, master1),
			new StudentModel("legolas.greenleaf", "ElvenArcher12", "Legolas Greenleaf", 1012, 16.5, master2),
			new StudentModel("merlin.wizard", "MagicStaff13", "Merlin Wizard", 1013, 14.5, master3),
			new StudentModel("nemo.clownfish", "JustKeepSwimming14", "Nemo Clownfish", 1014, 17.0, master1),
			new StudentModel("oliver.twist", "PleaseSir15", "Oliver Twist", 1015, 15.5, master2),
			new StudentModel("peter.pan", "NeverGrowUp16", "Peter Pan", 1016, 19.0, master3),
			new StudentModel("quentin.quirrell", "TurbansRock17", "Quentin Quirrell", 1017, 13.0, master1),
			new StudentModel("rapunzel.tower", "LetDownYourHair18", "Rapunzel Tower", 1018, 18.5, master2),
			new StudentModel("sherlock.holmes", "221BakerStreet19", "Sherlock Holmes", 1019, 17.5, master3),
			new StudentModel("tony.stark", "IronMan20", "Tony Stark", 1020, 16.0, master1)
		);
	}

	public void loadMasters() {

		ProfessorModel prof1 = new ProfessorModel("Kandonga", "hello", "mantorras");
		ProfessorModel prof2 = new ProfessorModel("Casemirao", "deus", "god");
		ProfessorModel prof3 = new ProfessorModel("Al-çides", "css", "git");

		MastersModel master1 = new MastersModel("Computer Science", prof1);
        MastersModel master2 = new MastersModel("Data Science", prof2);
        MastersModel master3 = new MastersModel("Software Engineering", prof3);

        mastersList.setAll(master1, master2, master3);
	}
}
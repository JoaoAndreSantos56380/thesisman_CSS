package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;
import pt.ul.fc.di.css.javafxexample.presentation.model.Professor;
import pt.ul.fc.di.css.javafxexample.presentation.model.Student;

public class ListController {

	//atencao ao nome
	@FXML
	private ListView<Student> listView ;

	private DataModel model;

	public void initModel(DataModel model) {
		if (this.model != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}

		this.model = model;
		listView.setItems(model.getList());

		listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
		model.setCurrentStudent(newSelection));
	}

}

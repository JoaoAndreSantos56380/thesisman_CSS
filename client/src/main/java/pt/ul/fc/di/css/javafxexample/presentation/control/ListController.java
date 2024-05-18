package pt.ul.fc.di.css.javafxexample.presentation.control;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pt.ul.fc.di.css.javafxexample.presentation.model.DataModel;

public class ListController<T> {

    @FXML
    private ListView<T> listView;

    private DataModel<T> model;

    public void initModel(DataModel<T> model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }

        this.model = model;
        listView.setItems(model.getItemsList());

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
            model.setCurrentItem(newSelection)
        );
    }
}

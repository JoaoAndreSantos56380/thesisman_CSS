package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//Muito simplificado, apenas para oder usar o Student
public class MastersModel {
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<ProfessorModel> coordenator = new SimpleObjectProperty<>();

    public MastersModel(String name, ProfessorModel coordenator) {
        setName(name);
        //alterar
        setCoordenator(coordenator);
    }

    public void setCoordenator(ProfessorModel coordenator) {
        this.coordenator.set(coordenator);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public SimpleObjectProperty<ProfessorModel> coordenatorProperty() {
        return (SimpleObjectProperty<ProfessorModel>) coordenator;
    }

    public String getName() {
        return name.get();
    }

    public ProfessorModel getCoordenator() {
        return coordenator.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return name.get();
    }
}

package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//Muito simplificado, apenas para oder usar o Student
public class Masters {
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Professor> coordenator = new SimpleObjectProperty<>();

    public Masters(String name, Professor coordenator) {
        setName(name);
        //alterar
        setCoordenator(coordenator);
    }

    public void setCoordenator(Professor coordenator) {
        this.coordenator.set(coordenator);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public SimpleObjectProperty<Professor> coordenatorProperty() {
        return (SimpleObjectProperty<Professor>) coordenator;
    }

    public String getName() {
        return name.get();
    }

    public Professor getCoordenator() {
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

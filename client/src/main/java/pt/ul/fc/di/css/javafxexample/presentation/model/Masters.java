package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//Muito simplificado, apenas para oder usar o Student
public class Masters {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty coordenator = new SimpleStringProperty();

    public Masters(String name, String coordenator) {
        setName(name);
        //alterar
        setCoordenator(coordenator);
    }

    public void setCoordenator(String coordenator) {
        this.coordenator.set(coordenator);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty coordenatorProperty() {
        return coordenator;
    }

    public String getName() {
        return name.get();
    }

    public String getCoordenator() {
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

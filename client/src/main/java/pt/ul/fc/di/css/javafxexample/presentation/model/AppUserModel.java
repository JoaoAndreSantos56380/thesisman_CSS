package pt.ul.fc.di.css.javafxexample.presentation.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
//import pt.ul.fc.css.example.demo.entities.AppUser;

public class AppUserModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();

    public AppUserModel(String username, String password, String name) {
        setUsername(username);
        setPassword(password);
        setName(name);
    }

    // Getters e setters para id
    public LongProperty idProperty() {
        return id;
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    // Getters e setters para username
    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}

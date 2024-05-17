package pt.ul.fc.di.css.javafxexample.presentation.model;

public class Professor extends AppUser {
    
    public Professor(String username, String password, String name) {
        super(username, password, name);
    }

    @Override
    public String toString() {
        return "Professor: " + ", username: " + getUsername()
                + ", name: " + getName()
                + ", password: " + getPassword();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Professor that = (Professor) obj;
        return getId() == that.getId() &&
                getUsername().equals(that.getUsername()) &&
                getName().equals(that.getName());
    }

}

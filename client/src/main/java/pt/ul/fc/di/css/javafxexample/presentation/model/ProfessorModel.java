package pt.ul.fc.di.css.javafxexample.presentation.model;

public class ProfessorModel extends AppUserModel {
    
    public ProfessorModel(String username, String password, String name) {
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
        ProfessorModel that = (ProfessorModel) obj;
        return getId() == that.getId() &&
                getUsername().equals(that.getUsername()) &&
                getName().equals(that.getName());
    }

}

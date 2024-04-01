package pt.ul.fc.css.projeto.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class Professor extends User {

  public Professor(String username, String password, String name) {
    super(username, password, name);
  }

  public Professor() {}
}

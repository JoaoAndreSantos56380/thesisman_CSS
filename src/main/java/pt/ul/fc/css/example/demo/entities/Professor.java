package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class Professor extends AppUser {

  public Professor(String username, String password, String name) {
    super(username, password, name);
  }

  public Professor() {}
}

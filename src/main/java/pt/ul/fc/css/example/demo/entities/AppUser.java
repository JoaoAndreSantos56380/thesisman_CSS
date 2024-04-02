package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String username;

  @NonNull private String password;

  @NonNull private String name;

  public AppUser(@NonNull String username, @NonNull String password, @NonNull String name) {
    this.username = username;
    this.password = password;
    this.name = name;
  }

  public AppUser() {}

  public long getId() {
    return id;
  }

  public String getName() {
    return username;
  }

  public void setName(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "AppUser["
        + "id="
        + getId()
        + ", "
        + "name="
        + getName()
        + ", "
        + "password="
        + getPassword()
        + ']';
  }
}

package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Column;
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

  @NonNull
  @Column(nullable = false, unique = true)
  private String username;

  @NonNull
  @Column(nullable = false)
  private String password;

  @NonNull
  @Column(nullable = false)
  private String name;

  public AppUser(@NonNull String username, @NonNull String password, @NonNull String name) {
    this.username = username;
    this.password = password;
    this.name = name;
  }

  public AppUser() {}

  public Long getId() {
    return id;
  }

  public String getUserName() {
    return username;
  }

  public String getName() {
    return name;
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

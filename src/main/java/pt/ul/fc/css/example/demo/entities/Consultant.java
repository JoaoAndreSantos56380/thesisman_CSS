package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
@DiscriminatorValue("CONSULTANT")
public class Consultant extends AppUser {
  public Consultant(String email, String password, String name, @NonNull String company) {
    super(email, password, name);
	this.company = company;
  }

  public Consultant() {}

  @NonNull private String company;

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "Consultant["
        + "id="
        + getId()
        + ", "
        + "name="
        + getName()
        + ", "
        + "company="
        + getCompany()
        + ']';
  }
}

package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.NonNull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSULTANT")
public class Consultant extends User {
	public Consultant(String email, String password, String name) {
		super(email, password, name);
	}

	public Consultant() {
	}

	@NonNull
	private String company;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}

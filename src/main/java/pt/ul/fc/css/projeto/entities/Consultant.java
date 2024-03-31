package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.Nullable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSULTANT")
public class Consultant extends User {
	public Consultant(String email, String password) {
		super(email, password);
	}
	public Consultant() {
	}

	@Nullable
	private String company;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}

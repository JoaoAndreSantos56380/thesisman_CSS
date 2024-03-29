package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.NonNull;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class Professor extends User {
	public Professor(String username, String password) {
		super(username, password);
	}

	public Professor(String username, String password, String emailField) {
		super(username, password);
		this.emailField = emailField;
	}

	@NonNull
	private String emailField;
}

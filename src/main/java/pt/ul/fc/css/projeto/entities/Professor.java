package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.NonNull;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class Professor extends User {

	public Professor(String username, String password, String name, Boolean isAdmin) {
		super(username, password, name);
		this.isAdmin = isAdmin;
	}

	public Professor() {
	}

	@NonNull
	private boolean isAdmin;

	public boolean getIsAdmin(){
		return isAdmin;
	}
}

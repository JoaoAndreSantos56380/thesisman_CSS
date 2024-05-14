package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AppUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;

	@NonNull
	@Column(nullable = false, unique = true)
	protected String username;

	@NonNull
	@Column(nullable = false)
	protected String password;

	@NonNull
	@Column(nullable = false)
	protected String name;

	public AppUser(@NonNull String username, @NonNull String password, @NonNull String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public AppUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

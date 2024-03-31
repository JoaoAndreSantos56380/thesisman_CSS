package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.NonNull;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@NonNull
	private String username;

	@NonNull
	private String password;

	@NonNull
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {
	}
}

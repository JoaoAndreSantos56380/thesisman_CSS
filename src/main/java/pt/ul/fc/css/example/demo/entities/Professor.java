package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

@Entity
@DiscriminatorValue("TEACHER")
public class Professor extends AppUser {

	public Professor(String username, String password, String name) {
		super(username, password, name);
	}

	public Professor() {
	}

	@Override
	public String toString() {
		return "Professor["
				+ "id="
				+ getId()
				+ ", "
				+ "username="
				+ getName()
				+ ", "
				+ "password="
				+ getPassword()
				+ ']';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		var that = (Professor) obj;
		return Objects.equals(this.getId(), that.getId())
				&& Objects.equals(this.getName(), that.getName());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

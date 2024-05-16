package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

@Entity
@DiscriminatorValue("CONSULTANT")
public class Consultant extends AppUser {
	public Consultant(String username, String password, String name, @NonNull String company) {
		super(username, password, name);
		this.company = company;
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

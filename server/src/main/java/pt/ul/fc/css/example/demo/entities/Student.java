package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends AppUser {
	public Student(
			@NonNull String username,
			@NonNull String password,
			@NonNull String name,
			@NonNull int studentNumber,
			@NonNull double averageGrade,
			@Nullable Masters master) {
		super(username, password, name);
		this.studentNumber = studentNumber;
		this.averageGrade = averageGrade;
		this.master = master;
	}

	public Student(
			@NonNull String username,
			@NonNull String password,
			@NonNull String name,
			@NonNull int studentNumber,
			@Nullable Masters master) {
		super(username, password, name);
		this.studentNumber = studentNumber;
		this.averageGrade = 0;
		this.master = master;
	}

	public Student() {
	}

	@NonNull
	private int studentNumber;

	// associacao many-to-one
	@NonNull

	@ManyToOne
	@JoinColumn(name = "fk_masters_id", referencedColumnName = "id")
	private Masters master;

	@NonNull
	private double averageGrade;

	public String getName() {
		return name;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public Masters getMasters() {
		return master;
	}

	public void setMaster(Masters master) {
		this.master = master;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	@Override
	public String toString() {
		return "Student[" + "id=" + getId() + ", " + "name=" + getName() + "]";
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

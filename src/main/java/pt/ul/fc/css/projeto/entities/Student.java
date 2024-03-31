package pt.ul.fc.css.projeto.entities;


import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("STUDENT")
// @Table(name = "student")
public class Student extends User {
	public Student(String email, String password) {
		super(email, password);
	}

	public Student() {
	}

	@NonNull
	private int studentNumber;

	//associacao many-to-one
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_masters_id", referencedColumnName = "id")
	private Masters masters;

	@NonNull
	private double averageGrade;

	/* @Nullable
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_application_id", referencedColumnName = "id") */
	//private ArrayList<Application> application;

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Masters getMasters() {
		return masters;
	}

	public void setMasters(Masters masters) {
		this.masters = masters;
	}

	public void setAverageGrade(float averageGrade) {
		this.averageGrade = averageGrade;
	}
}

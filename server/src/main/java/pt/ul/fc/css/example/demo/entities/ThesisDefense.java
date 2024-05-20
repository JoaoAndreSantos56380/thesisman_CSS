package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("FIRST")
public class ThesisDefense {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "fk_thesisExecution_id", referencedColumnName = "id", nullable = false)
	private ThesisExecution thesisExecution;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "fk_arguente_id", referencedColumnName = "id", nullable = true)
	private Professor arguente;

	/*
	 *
	 * @NonNull*
	 *
	 * @Lob
	 * private File manuscriptFile;
	 */

	@Nullable
	@Column(nullable = true)
	private int grade;

	@NonNull
	@Column(nullable = false)
	private String location;

	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(nullable = false)
	private Date time;

	public ThesisDefense(ThesisExecution thesisExecution, String location, Date time, Professor arguente) {
		this.thesisExecution = thesisExecution;
		this.location = location;
		this.time = time;
		this.arguente = arguente;
		this.grade = -1; // Including grade in this constructor
	}

	public ThesisDefense() {
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Professor getArguente() {
		return arguente;
	}

	public void setArguente(Professor arguente) {
		this.arguente = arguente;
	}

	public long getId() {
		return id;
	}

	public ThesisExecution getThesisExecution() {
		return thesisExecution;
	}

	public int getGrade() {
		return grade;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}

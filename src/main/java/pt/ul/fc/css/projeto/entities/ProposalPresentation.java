package pt.ul.fc.css.projeto.entities;

import java.util.Date;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProposalPresentation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_thesisExecution_id", referencedColumnName = "id")
	private ThesisExecution thesisExecution;

	/* @NonNull
	@Lob
	private File manuscriptFile; */

	@Nullable
	private double grade;

	@NonNull
	private String location;

	@NonNull
	private Date time;

	public void assignGrade(int grade){
		this.grade = grade;
	}

	public long getId() {
		return id;
	}

	public ThesisExecution getThesisExecution() {
		return thesisExecution;
	}

	public double getGrade() {
		return grade;
	}
}

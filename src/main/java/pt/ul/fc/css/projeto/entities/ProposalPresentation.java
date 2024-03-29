package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
//import jakarta.persistence.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProposalPresentation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_thesisExecution_id", referencedColumnName = "id")
	private ThesisExecution thesisExecution;

	/* @NonNull
	@Lob
	private File manuscriptFile; */

	@Nullable
	private int grade;

	@NonNull
	private String location;

	public void assignGrade(int grade){
		this.grade = grade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ThesisExecution getThesisExecution() {
		return thesisExecution;
	}

	public void setThesisExecution(ThesisExecution thesisExecution) {
		this.thesisExecution = thesisExecution;
	}

	public float getGrade() {
		return grade;
	}
}

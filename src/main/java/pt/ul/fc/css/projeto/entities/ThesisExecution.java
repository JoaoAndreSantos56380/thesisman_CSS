package pt.ul.fc.css.projeto.entities;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class ThesisExecution {
	@Id
	private int id;

	//associacao one-to-one
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_student_id", referencedColumnName = "id")
	private Student student;

	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id")
	private DissertationTopic topic;

	private Date yearOfExecution;

	private int finalGrade;

	//nao e suposto a execucao conhecer as defesas mas sim o contrario
	//private ArrayList<ThesisDefense> proposalDefenses;

	public void setFinalGrade(int grade){
		finalGrade = grade;
	}
}

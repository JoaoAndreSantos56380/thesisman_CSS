package pt.ul.fc.css.projeto.entities;


import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Application {
	@Id
	private int id;

	@NonNull
	//associacao one-to-many
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_student_id", referencedColumnName = "id")
	private Student student;


	//ordem de preferencia do topico da tese
	@NonNull
	private int order;

	//@NonNull
	//media estudante arredondada
	//retirar pois a media do estudande vem no student e teriamos repeticao
	//private int grade;

	//associacao one-to-one
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id")
	private DissertationTopic topic;
}

package pt.ul.fc.css.projeto.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Masters {
	@Id
	private int id;

	@NonNull
	private String name;

	//associacao 1-1 com o professor
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_professor_id", referencedColumnName = "id")
	private Professor coordinator;
}

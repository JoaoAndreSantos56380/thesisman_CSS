package pt.ul.fc.css.projeto.entities;

import java.util.ArrayList;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class DissertationTopic {
	@Id
	private int id;

	private String title;

	private String description;

	private int salary;

	//associacao many-to-one
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_professor_id", referencedColumnName = "id")
	private Professor internalAdvisor;


	//associacao many-to-one
	@Nullable
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_consultant_id", referencedColumnName = "id")
	private Consultant externalAdvisor;

	@Nullable
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_masters_id", referencedColumnName = "id")
	private ArrayList<Masters> compatibleMasters;

	private int year;

}

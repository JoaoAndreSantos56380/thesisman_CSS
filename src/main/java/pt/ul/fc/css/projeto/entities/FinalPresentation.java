package pt.ul.fc.css.projeto.entities;

import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class FinalPresentation extends ProposalPresentation{
	//associacao many-to-one
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_president_id", referencedColumnName = "id")
	private Professor president;
}

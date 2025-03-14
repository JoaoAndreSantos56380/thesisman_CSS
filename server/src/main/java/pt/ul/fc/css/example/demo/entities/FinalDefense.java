package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import org.springframework.lang.NonNull;

@Entity
@DiscriminatorValue("FINAL")
public class FinalDefense extends ThesisDefense {
	// associacao many-to-one
	@NonNull
	@ManyToOne
	@JoinColumn(name = "fk_president_id", referencedColumnName = "id")
	private Professor president;

	public FinalDefense(
			ThesisExecution thesisExecution, String location, Date time, Professor president, Professor arguente) {
		super(thesisExecution, location, time, arguente);
		this.president = president;
	}

	public FinalDefense() {
	}

	public Professor getPresident() {
		return president;
	}

	public void setPresident(Professor president) {
		this.president = president;
	}
}

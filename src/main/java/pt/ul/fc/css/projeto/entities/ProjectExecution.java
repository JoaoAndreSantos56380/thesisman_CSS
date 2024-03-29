package pt.ul.fc.css.projeto.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
public class ProjectExecution extends ThesisExecution {
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_consultant_id", referencedColumnName = "id")
	private Consultant consultant;

	public Consultant getConsultant() {
		return consultant;
	}

	public void setConsultantProfile(Consultant consultant) {
		this.consultant = consultant;
	}
}

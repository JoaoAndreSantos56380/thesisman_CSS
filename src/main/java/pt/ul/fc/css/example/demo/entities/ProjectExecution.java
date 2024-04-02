package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ProjectExecution extends ThesisExecution {
  @NonNull
  @ManyToOne // (cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_consultant_id", referencedColumnName = "id")
  private Consultant consultant;

  public ProjectExecution() {
    super();
  }

  public ProjectExecution(
      Student student, DissertationTopic topic, Date yearOfExecution, Integer finalGrade) {
    super(student, topic, yearOfExecution, finalGrade);
    this.consultant = (Consultant) topic.getSubmitter();
  }

  public Consultant getConsultant() {
    return consultant;
  }

  public void setConsultantProfile(Consultant consultant) {
    this.consultant = consultant;
  }
}

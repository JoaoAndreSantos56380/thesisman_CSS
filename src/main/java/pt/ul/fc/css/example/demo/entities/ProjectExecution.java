package pt.ul.fc.css.example.demo.entities;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjectExecution extends ThesisExecution {
  @NonNull
  @ManyToOne //(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_consultant_id", referencedColumnName = "id")
  private Consultant consultant;

  public ProjectExecution() {
    super();
  }

  public ProjectExecution(Student student, DissertationTopic topic, Date yearOfExecution, Integer finalGrade, Consultant consultant) {
    super(student, topic, yearOfExecution, finalGrade);
    this.consultant = consultant;
}

  public Consultant getConsultant() {
    return consultant;
  }

  public void setConsultantProfile(Consultant consultant) {
    this.consultant = consultant;
  }
}

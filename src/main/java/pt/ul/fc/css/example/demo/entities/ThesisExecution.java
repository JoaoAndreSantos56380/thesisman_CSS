package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Date;

@Entity
public class ThesisExecution {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  // associacao one-to-one
  @NonNull
  @OneToOne //(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_student_id", referencedColumnName = "id")
  private Student student;

  @NonNull
  @OneToOne //(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id")
  private DissertationTopic topic;

  @NonNull private Date yearOfExecution;

  @Nullable private int finalGrade;

  public void setFinalGrade(int grade) {
    finalGrade = grade;
  }

  public ThesisExecution(Student student, DissertationTopic topic, Date yearOfExecution, Integer finalGrade) {
  	this.student = student;
  	this.topic = topic;
  	this.yearOfExecution = yearOfExecution;
  	this.finalGrade = finalGrade;
    this.student = student;
    this.topic = topic;
    this.yearOfExecution = yearOfExecution;
    this.finalGrade = finalGrade;
  }

  public ThesisExecution() {}
}

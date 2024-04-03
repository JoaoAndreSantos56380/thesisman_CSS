package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ThesisExecution {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  // associacao one-to-one
  @NonNull
  @OneToOne // (cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_student_id", referencedColumnName = "id", nullable = false)
  private Student student;

  @NonNull
  @OneToOne // (cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id", nullable = false)
  private DissertationTopic topic;

  @NonNull
  @ManyToOne // (cascade = CascadeType.PERSIST)
  @JoinColumn(name = "fk_internal_advisor_id", referencedColumnName = "id", nullable = false)
  private Professor internalAdvisor;

  @NonNull
  @Column(nullable = false)
  private String yearOfExecution;

  @Nullable
  @Column(nullable = true)
  private int finalGrade;

  public void setFinalGrade(int grade) {
    finalGrade = grade;
  }

  public Student getStudent() {
    return this.student;
  }

  public ThesisExecution(Student student, DissertationTopic topic, String yearOfExecution) {
    if (topic.getType() == DissertationTopicType.PROJECT) {
      throw new IllegalArgumentException(
          "Trying to create a ThesisExecution from a Project topic. Use ProjectExecution!");
    }
    this.student = student;
    this.topic = topic;
    this.yearOfExecution = yearOfExecution;
    this.internalAdvisor = (Professor) topic.getSubmitter();
  }

  public ThesisExecution(
      Student student, DissertationTopic topic, String yearOfExecution, Professor internalAdvisor) {
    if (topic.getType() == DissertationTopicType.DISSERTATION) {
      throw new IllegalArgumentException("This constructor is for Project Topics!");
    }
    this.student = student;
    this.topic = topic;
    this.yearOfExecution = yearOfExecution;
    this.internalAdvisor = internalAdvisor;
  }

  public ThesisExecution() {}
}

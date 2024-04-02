package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull
  // associacao one-to-many
  @ManyToOne // (cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_student_id", referencedColumnName = "id")
  private Student student;

  // associacao one-to-one
  @NonNull
  @ManyToOne // (cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id")
  private DissertationTopic topic;

  public Application(Student student, DissertationTopic topic) {
    this.student = student;
    this.topic = topic;
  }

  public Long getId() {
    return this.id;
  }

  public Student getStudent() {
    return this.student;
  }

  public DissertationTopic getTopic() {
    return this.topic;
  }

  @Override
  public String toString() {
    return "Application["
        + "id="
        + getId()
        + ", "
        + "student="
        + getStudent()
        + ", "
        + "topic="
        + getTopic()
        + "]";
  }
}

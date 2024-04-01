package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
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
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_student_id", referencedColumnName = "id")
  private Student student;

  // associacao one-to-one
  @NonNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id")
  private DissertationTopic topic;
}

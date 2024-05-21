package pt.ul.fc.css.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ThesisExecution {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  // associacao one-to-one
  @NonNull
  @OneToOne
  @JoinColumn(name = "fk_student_id", referencedColumnName = "id", nullable = false, unique = true)
  private Student student;

  @NonNull
  @OneToOne
  @JoinColumn(name = "fk_dissertation_topic_id", referencedColumnName = "id", nullable = false, unique = true)
  private DissertationTopic topic;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "fk_internal_advisor_id", referencedColumnName = "id", nullable = false)
  private Professor internalAdvisor;

  @NonNull
  @Column(nullable = true)
  private String yearOfExecution;

  @Nullable
  @Column(nullable = true)
  private int finalGrade;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "thesis_execution_id")
  private Set<Document> documents = new HashSet<>();


  public void setFinalGrade(int grade) {
    finalGrade = grade;
  }

  public Student getStudent() {
    return this.student;
  }

  public Long getId() {
    return id;
  }

  public Professor getInternalAdvisor() {
    return this.internalAdvisor;
  }

  public void setInternalAdvisor(Professor internalAdvisor) {
    this.internalAdvisor = internalAdvisor;
  }

  public int getFinalGrade() {
    return this.finalGrade;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public void setTopic(DissertationTopic topic) {
    this.topic = topic;
  }

  public void setYearOfExecution(String yearOfExecution) {
    this.yearOfExecution = yearOfExecution;
  }
  public DissertationTopic getTopic() {
    return this.topic;
  }

  public String getYearOfExecution() {
    return this.yearOfExecution;
  }

  public void addDocument(Document document) {
    this.documents.add(document);
  }

  public Set<Document> getDocuments() {
    return documents;
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

package pt.ul.fc.css.example.demo.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.lang.NonNull;

@Entity
public class DissertationTopic {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull
  @Column(nullable = false)
  private DissertationTopicType type;

  @NonNull
  @Column(nullable = false)
  private String title;

  @NonNull
  @Column(nullable = false)
  private String description;

  @NonNull
  @Column(nullable = false)
  private double salary;

  // associacao many-to-one
  @NonNull
  @ManyToOne // (cascade = CascadeType.PERSIST)
  @JoinColumn(name = "fk_submitter_id", referencedColumnName = "id", nullable = false)
  private AppUser submitter;

  @NonNull
  @ManyToMany // (cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "dissertation_topic_compatible_masters",
      joinColumns = @JoinColumn(name = "dissertation_topic_id"),
      inverseJoinColumns = @JoinColumn(name = "master_id"))
  // @JoinColumn(name = "fk_masters_id", referencedColumnName = "id")
  private Set<Masters> compatibleMasters;

  public long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public double getSalary(){
	return this.salary;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Masters> getCompatibleMasters() {
    return this.compatibleMasters;
  }

  public DissertationTopicType getType() {
    return this.type;
  }

  public AppUser getSubmitter() {
    return this.submitter;
  }

  public DissertationTopic() {}

  public DissertationTopic(
      @NonNull String title,
      @NonNull String description,
      @NonNull double salary,
      @NonNull AppUser submitter,
      @Nullable Set<Masters> compatibleMasters) {
    this.title = title;
    this.description = description;
    this.salary = salary;
    this.submitter = submitter;
    this.compatibleMasters = compatibleMasters;

    if (submitter.getClass() == Professor.class) {
      this.type = DissertationTopicType.DISSERTATION;
    } else {
      this.type = DissertationTopicType.PROJECT;
    }
  }

  @Override
  public String toString() {
    return "DissertationTopic["
        + "id="
        + getId()
        + ", "
        + "title="
        + getTitle()
        + ", "
        + "description="
        + getDescription()
        + ']';
  }
}

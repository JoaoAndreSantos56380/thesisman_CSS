package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
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

@Entity
public class DissertationTopic {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String title;

  @NonNull private String description;

  @NonNull private double salary;

  // associacao many-to-one, este cascade da erro
  @NonNull
  @ManyToOne //(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_professor_id", referencedColumnName = "id")
  private Professor internalAdvisor;

  // associacao many-to-one
  @Nullable
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "fk_consultant_id", referencedColumnName = "id")
  private Consultant externalAdvisor;

  @Nullable
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "dissertation_topic_compatible_masters",
      joinColumns = @JoinColumn(name = "dissertation_topic_id"),
      inverseJoinColumns = @JoinColumn(name = "master_id"))
  // @JoinColumn(name = "fk_masters_id", referencedColumnName = "id")
  private List<Masters> compatibleMasters;

  public long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
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

  public Consultant getConsultant() {
    return this.externalAdvisor;
  }

  public DissertationTopic() {}

  public DissertationTopic(@NonNull String title, @NonNull String description, @NonNull double salary, @NonNull Professor internalAdvisor, @Nullable Consultant externalAdvisor, @Nullable ArrayList<Masters> compatibleMasters) {
    this.title = title;
    this.description = description;
    this.salary = salary;
    this.internalAdvisor = internalAdvisor;
    this.externalAdvisor = externalAdvisor;
    this.compatibleMasters = compatibleMasters;
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

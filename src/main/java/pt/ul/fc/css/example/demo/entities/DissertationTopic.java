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

@Entity
public class DissertationTopic {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String title;

  @NonNull private String description;

  @NonNull private double salary;

  // associacao many-to-one
  @NonNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_professor_id", referencedColumnName = "id")
  private Professor internalAdvisor;

  // associacao many-to-one
  @Nullable
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_consultant_id", referencedColumnName = "id")
  private Consultant externalAdvisor;

  @Nullable
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
        name = "dissertation_topic_compatible_masters",
        joinColumns = @JoinColumn(name = "dissertation_topic_id"),
        inverseJoinColumns = @JoinColumn(name = "master_id")
    )
  //@JoinColumn(name = "fk_masters_id", referencedColumnName = "id")
  private ArrayList<Masters> compatibleMasters;

  public DissertationTopic() {}
}

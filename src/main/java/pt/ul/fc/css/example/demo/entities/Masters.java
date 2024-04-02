package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Masters {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String name;

  // associacao 1-1 com o professor
  @NonNull
  @OneToOne // (cascade = CascadeType.PERSIST)
  @JoinColumn(name = "fk_professor_id", referencedColumnName = "id")
  private Professor coordinator;

  public Masters(@NonNull String name, @NonNull Professor coordinator) {
    this.name = name;
    this.coordinator = coordinator;
  }

  public String getName() {
    return name;
  }

  public Professor getCoordinator() {
    return coordinator;
  }

  public Long getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Masters) obj;
    return Objects.equals(this.getId(), that.getId())
        && Objects.equals(this.getName(), that.getName())
        && Objects.equals(this.getCoordinator(), that.getCoordinator());
  }

  public Masters() {}
}

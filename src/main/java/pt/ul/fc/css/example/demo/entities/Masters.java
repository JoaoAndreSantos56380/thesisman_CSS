package pt.ul.fc.css.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Masters {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull private String name;

  // associacao 1-1 com o professor
  @NonNull
  @OneToOne //(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "fk_professor_id", referencedColumnName = "id")
  private Professor coordinator;

  public Masters(@NonNull String name, @NonNull Professor coordinator) {
  	this.name = name;
  	this.coordinator = coordinator;
  }

  public String getName(){
	return name;
  }

  public Masters() {}
}

package pt.ul.fc.css.projeto.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class FinalPresentation extends ThesisDefense {
  // associacao many-to-one
  @NonNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_president_id", referencedColumnName = "id")
  private Professor president;
}

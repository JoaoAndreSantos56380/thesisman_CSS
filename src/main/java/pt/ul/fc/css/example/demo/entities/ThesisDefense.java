package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
public class ThesisDefense {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "fk_thesisExecution_id", referencedColumnName = "id")
  private ThesisExecution thesisExecution;

  /*
   * @NonNull
   *
   * @Lob
   * private File manuscriptFile;
   */

  @Nullable private double grade;

  @NonNull private String location;

  @NonNull private Date time;

  	public ThesisDefense(ThesisExecution thesisExecution, String location, Date time, double grade) {
		this.thesisExecution = thesisExecution;
		this.location = location;
		this.time = time;
		this.grade = grade; // Including grade in this constructor
	}

  public void assignGrade(int grade) {
    this.grade = grade;
  }

  public long getId() {
    return id;
  }

  public ThesisExecution getThesisExecution() {
    return thesisExecution;
  }

  public double getGrade() {
    return grade;
  }
}

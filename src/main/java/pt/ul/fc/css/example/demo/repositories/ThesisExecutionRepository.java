package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;

public interface ThesisExecutionRepository extends JpaRepository<ThesisExecution, Long> {
  @Query("SELECT t FROM ThesisExecution t WHERE t.student = :student")
  List<ThesisExecution> findByStudent(@Param("student") Student student);
}

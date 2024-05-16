package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;

public interface ThesisExecutionRepository extends JpaRepository<ThesisExecution, Long> {
  @Query("SELECT t FROM ThesisExecution t WHERE t.student = :student")
  List<ThesisExecution> findByStudent(@Param("student") Student student);

  @Query("SELECT t FROM ThesisExecution t WHERE t.topic = :topic")
  List<ThesisExecution> findByDissertationTopic(@Param("topic") DissertationTopic topic);

  @Query("SELECT t FROM ThesisExecution t WHERE t.yearOfExecution LIKE %:year%")
  List<ThesisExecution> findByYearOfExecution(@Param("year") String year);

  @Query("SELECT t FROM ThesisExecution t WHERE t.finalGrade = :finalGrade")
  List<ThesisExecution> findByFinalGrade(@Param("finalGrade") Integer finalGrade);

  @Query("SELECT t FROM ThesisExecution t WHERE t.internalAdvisor = :internalAdvisor")
  List<ThesisExecution> findByInternalAdvisor(@Param("internalAdvisor") Professor internalAdvisor);

}
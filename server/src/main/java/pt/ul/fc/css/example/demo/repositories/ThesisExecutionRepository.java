package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import java.util.Optional;

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

	@Query("SELECT t FROM ThesisExecution t LEFT JOIN ThesisDefense d ON t.id = d.thesisExecution.id WHERE t.internalAdvisor = :internalAdvisor AND (d.id IS NULL OR TYPE(d) = 'FIRST' AND (NOT EXISTS (SELECT d2.id FROM ThesisDefense d2 WHERE d2.thesisExecution.id = t.id AND d2.grade = -1) AND NOT EXISTS (SELECT d2.id FROM ThesisDefense d2 WHERE d2.thesisExecution.id = t.id AND d2.grade >= 10)))")
	List<ThesisExecution> findUnscheduledTheses(@Param("internalAdvisor") Professor internalAdvisor);

	@Query("SELECT te FROM ThesisExecution te LEFT JOIN FinalDefense fd ON fd.thesisExecution.id = te.id WHERE fd.id IS NULL AND te.internalAdvisor = :internalAdvisor")
	List<ThesisExecution> findScheduledFinal(@Param("internalAdvisor") Professor internalAdvisor);

	@Query("SELECT t FROM ThesisExecution t LEFT JOIN ThesisDefense d ON t.id = d.thesisExecution.id WHERE t.internalAdvisor = :internalAdvisor AND EXISTS (SELECT d2.id FROM ThesisDefense d2 WHERE d2.thesisExecution.id = t.id AND d2.grade > 9) AND NOT EXISTS (SELECT d2.id FROM ThesisDefense d2 WHERE d2.thesisExecution.id = t.id AND TYPE(d2) = 'FINAL')")
	List<ThesisExecution> findUnscheduledFinalTheses(@Param("internalAdvisor") Professor internalAdvisor);

	@Query("SELECT t FROM ThesisExecution t WHERE t.student.id = :studentId")
	Optional<ThesisExecution> findByStudentID(@Param("studentId") Long studentId);

}
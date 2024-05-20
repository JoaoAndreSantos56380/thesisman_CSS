package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.example.demo.entities.AppUser;
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

	@Query("SELECT t FROM ThesisExecution t LEFT JOIN ThesisDefense d ON t.id = d.thesisExecution.id WHERE t.internalAdvisor = :internalAdvisor AND (d.id IS NULL OR (d.id IS NOT NULL AND d.grade < 10 AND d.grade != -1))")
	List<ThesisExecution> findUnscheduledTheses(@Param("internalAdvisor") Professor internalAdvisor);

	@Query("SELECT te FROM ThesisExecution te LEFT JOIN ThesisDefense td ON te.id = td.thesisExecution.id WHERE (td.grade = -1 AND te.internalAdvisor = :internalAdvisor)")
	List<ThesisExecution> findScheduledTheses(@Param("internalAdvisor") Professor internalAdvisor);

	//@Query("SELECT te FROM ThesisExecution te LEFT JOIN FinalDefense td ON te.id = td.thesisExecution.id WHERE td.id IS NULL AND te.internalAdvisor = :internalAdvisor")
	//@Query("SELECT te FROM ThesisExecution te JOIN te.thesisDefenses td WHERE td.grade > 10 AND te.id NOT IN (SELECT fe.thesisExecution.id FROM FinalDefense fe")
	//@Query("SELECT te FROM ThesisExecution te LEFT JOIN te.thesisDefenses td WHERE td IS NULL OR td.grade < 10")
	//@Query("SELECT te FROM ThesisExecution te LEFT JOIN FinalDefense fd ON fd.thesisExecution= te WHERE fd.id IS NULL")
	@Query("SELECT te FROM ThesisExecution te LEFT JOIN FinalDefense fd ON fd.thesisExecution.id = te.id WHERE fd.id IS NULL AND te.internalAdvisor = :internalAdvisor")
	List<ThesisExecution> findScheduledFinal(@Param("internalAdvisor") Professor internalAdvisor);

}

package pt.ul.fc.css.example.demo.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;

public interface DefenseRepository extends JpaRepository<ThesisDefense, Long> {
  @Query("SELECT d FROM ThesisDefense d WHERE d.thesisExecution.student = :student")
  List<ThesisDefense> findByStudent(@Param("student") Student student);

  @Query("SELECT d FROM ThesisDefense d WHERE d.location = :location")
  List<ThesisDefense> findByLocation(@Param("location") String location);

  @Query("SELECT d FROM ThesisDefense d WHERE d.time = :time")
  List<ThesisDefense> findByTime(@Param("time") Date time);

  @Query("SELECT td FROM ThesisDefense td WHERE td.grade > 9")
  List<ThesisDefense> findByGradeAboveTen();

  @Query("SELECT td FROM ThesisDefense td JOIN td.thesisExecution te WHERE te.internalAdvisor = :professor")
  List<ThesisDefense> getDefensesOfTheThesesIAmOrienting(@Param("professor") Professor professor);
}

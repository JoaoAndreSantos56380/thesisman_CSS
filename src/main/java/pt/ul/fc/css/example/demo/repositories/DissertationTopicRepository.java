package pt.ul.fc.css.example.demo.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;

public interface DissertationTopicRepository extends JpaRepository<DissertationTopic, Long> {

  @Transactional
  @Modifying
  @Query("SELECT dt FROM DissertationTopic dt WHERE dt.title LIKE %:q% ")
  List<DissertationTopic> findByTitle(@Param("q") String q);


  /*
  @Transactional
  @Modifying
  @Query("SELECT * FROM DissertationTopic d WHERE d.id = :dId")
  void findId(@Param("dId") Long dId);
/**
  @Transactional
  @Modifying
  @Query("update Student s set s.studentNumber = :studentNumber where s.id = :userId")
  void updateStudentNumber(
      @Param("userId") Long userId, @Param("studentNumber") Integer studentNumber);
  */

  	@Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
	List<DissertationTopic> findByName(@Param("q") String q);

	public void addDissertationTopic(DissertationTopic topic);

	public void removeDissertationTopic(DissertationTopic topic);
}

package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Student;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
  @Query("SELECT a FROM Application a WHERE a.student = :student")
  List<Application> findByStudent(@Param("student") Student student);

  @Query("SELECT a FROM Application a WHERE a.topic = :topic")
  List<Application> findByTopic(@Param("topic") DissertationTopic topic);
}

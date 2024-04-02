package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;

public interface DissertationTopicRepository extends JpaRepository<DissertationTopic, Long> {

  @Query("SELECT d FROM DissertationTopic d WHERE d.internalAdvisor = :advisor")
  List<DissertationTopic> findByInternalAdvisor(@Param("advisor") Professor advisor);

  @Query("SELECT d FROM DissertationTopic d WHERE d.externalAdvisor = :advisor")
  List<DissertationTopic> findByExternalAdvisor(@Param("advisor") Consultant advisor);

  @Query("SELECT d FROM DissertationTopic d JOIN d.compatibleMasters m WHERE m = :masters")
  List<DissertationTopic> findByCompatibleMasters(@Param("masters") Masters masters);

  @Query("SELECT dt FROM DissertationTopic dt WHERE dt.title LIKE %:q% ")
  List<DissertationTopic> findByTitle(@Param("q") String q);
}

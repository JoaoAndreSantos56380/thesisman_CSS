package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;

public interface DissertationTopicRepository extends JpaRepository<DissertationTopic, Long> {

	@Query("SELECT d FROM DissertationTopic d WHERE d.submitter = :submitter")
	List<DissertationTopic> findBySubmitter(@Param("submitter") AppUser submitter);

	@Query("SELECT d FROM DissertationTopic d JOIN d.compatibleMasters m WHERE m = :masters")
	List<DissertationTopic> findByCompatibleMasters(@Param("masters") Masters masters);

	@Query("SELECT dt FROM DissertationTopic dt WHERE dt.title LIKE %:q% ")
	List<DissertationTopic> findByTitle(@Param("q") String q);

	@Query("SELECT dt FROM DissertationTopic dt LEFT JOIN ThesisExecution te ON dt.id = te.topic.id WHERE te.id IS NULL")
	List<DissertationTopic> findFreeTopics();
}

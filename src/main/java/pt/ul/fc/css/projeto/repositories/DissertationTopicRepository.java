package pt.ul.fc.css.projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.projeto.entities.DissertationTopic;

public interface DissertationTopicRepository extends JpaRepository<DissertationTopic, Integer> {

    @Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
    List<DissertationTopic> findByName(@Param("q") String q);

    public void addDissertationTopic(DissertationTopic topic); 

    public void removeDissertationTopic(DissertationTopic topic); 
}
package pt.ul.fc.css.projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.projeto.entities.ThesisExecution;

public interface ThesisRepository extends JpaRepository<ThesisExecution, Integer> {

    @Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
    List<ThesisExecution> findByName(@Param("q") String q);

    public void addThesis(ThesisExecution thesis);

    public void removeThesis(ThesisExecution thesis);

}
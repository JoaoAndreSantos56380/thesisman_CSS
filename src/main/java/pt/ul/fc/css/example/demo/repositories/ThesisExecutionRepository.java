package pt.ul.fc.css.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.example.demo.entities.ThesisExecution;

public interface ThesisExecutionRepository extends JpaRepository<ThesisExecution, Long>{
	@Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
    List<ThesisExecution> findByName(@Param("q") String q);

    public void addThesis(ThesisExecution thesis);

    public void removeThesis(ThesisExecution thesis);
}

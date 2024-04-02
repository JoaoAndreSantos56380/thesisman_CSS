package pt.ul.fc.css.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.example.demo.entities.ThesisDefense;

public interface DefenseRepository extends JpaRepository<ThesisDefense, Long>{
	/* @Query("SELECT a FROM Masters a WHERE a.name LIKE %:q% ")
    List<ThesisDefense> findByName(@Param("q") String q);

    public void addProposalPresentation(ThesisDefense proposal);

    public void removeProposalPresentation(ThesisDefense proposal); */
}

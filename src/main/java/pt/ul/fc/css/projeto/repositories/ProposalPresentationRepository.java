package pt.ul.fc.css.projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.projeto.entities.ProposalPresentation;

public interface ProposalPresentationRepository extends JpaRepository<ProposalPresentation, Integer> {

    @Query("SELECT a FROM Masters a WHERE a.name LIKE %:q% ")
    List<ProposalPresentationRepository> findByName(@Param("q") String q);

    public void addProposalPresentation(ProposalPresentation proposal);
    
    public void removeProposalPresentation(ProposalPresentation proposal); 
}
package pt.ul.fc.css.projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.projeto.entities.Masters;

public interface MastersRepository extends JpaRepository<Masters, Integer> {

    @Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
    List<Masters> findByName(@Param("q") String q);

    public void addMasters(Masters masters);   

    public void removeMasters(Masters masters); 
}
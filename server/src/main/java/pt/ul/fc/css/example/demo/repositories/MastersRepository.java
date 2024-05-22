package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;

public interface MastersRepository extends JpaRepository<Masters, Long> {

  @Query("SELECT a FROM Masters a WHERE a.name LIKE %:q% ")
  Optional<Masters> findByName(@Param("q") String q);

  @Query("SELECT m FROM Masters m WHERE m.coordinator = :professor")
  List<Masters> findByCoordinator(@Param("professor") Professor professor);


}
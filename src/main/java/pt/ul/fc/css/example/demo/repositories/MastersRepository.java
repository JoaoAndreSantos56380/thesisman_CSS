package pt.ul.fc.css.example.demo.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.Masters;

public interface MastersRepository extends JpaRepository<Masters, Long> {

  // Custom query to find Masters by name
  @Transactional
  @Modifying
  @Query("SELECT m FROM Masters m WHERE m.name LIKE %:q% ")
  List<Masters> findByName(@Param("q") String name);

  // Custom query to find Masters by coordinator
  /*
   * @Transactional
   *
   * @Modifying
   *
   * @Query("SELECT m FROM Masters m WHERE m.coordinator.id = :userId")
   * List<Masters> findByCoordinator(@Param(":userId") Professor coordinator);
   */
}

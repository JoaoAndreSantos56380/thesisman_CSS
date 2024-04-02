package pt.ul.fc.css.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ul.fc.css.example.demo.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	/* @Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
    List<Application> findByName(@Param("q") String q);

    public void addApplication(Application application);

    public void removeApplication(Application application);  */
}

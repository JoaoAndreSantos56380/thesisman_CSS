package pt.ul.fc.css.example.demo.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Masters;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	@Query("SELECT a FROM AppUser a WHERE a.name LIKE %:q% ")
	List<AppUser> findByName(@Param("q") String q);

	@Query("SELECT a FROM AppUser a WHERE a.username LIKE %:q% ")
	List<AppUser> findByUserName(@Param("q") String q);

	@Query("SELECT a FROM AppUser a WHERE a.studentNumber = :number ")
	List<AppUser> findByStudentNumber(@Param("number") int number);

	@Query("SELECT a FROM AppUser a WHERE a.averageGrade = :averageGrade ")
	List<AppUser> findByAvgGrade(@Param("averageGrade") double averageGrade);

	@Query("SELECT a FROM AppUser a WHERE a.master.id = :masterId ")
	List<AppUser> findByMasterId(@Param("masterId") Long masterrId);

	@Query("SELECT a FROM AppUser a WHERE a.master = :master ")
	List<AppUser> findByMaster(@Param("master") Masters master);

	@Query("SELECT a FROM AppUser a WHERE a.company LIKE %:company% ")
	List<AppUser> findByCompany(@Param("company") String company);




}

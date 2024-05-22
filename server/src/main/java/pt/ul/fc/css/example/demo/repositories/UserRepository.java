package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Masters;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	@Query("SELECT a FROM AppUser a WHERE a.name LIKE %:q% ")
	List<AppUser> findByName(@Param("q") String q);

	@Query("SELECT a FROM AppUser a WHERE a.username = :q")
	Optional<AppUser> findByUserName(@Param("q") String q);

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

	@Query("SELECT a FROM AppUser a WHERE TYPE(a) = :type")
	List<AppUser> findByType(@Param("type") Class<? extends AppUser> type);

	@Query("SELECT a FROM AppUser a LEFT JOIN ThesisExecution te ON a.id = te.student.id WHERE TYPE(a) = 'STUDENT' AND te.id IS NULL")
	List<AppUser> findFreeStudents();

	@Query("SELECT a FROM AppUser a WHERE TYPE(a) = 'TEACHER' AND a.id <> :id")
	List<AppUser> findAllProfessorsExceptMe(Long id);

	@Query("SELECT u FROM AppUser u WHERE u.username = :username")
	Optional<AppUser> findByUsername(@Param("username") String username);

}

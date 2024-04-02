package pt.ul.fc.css.example.demo.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.example.demo.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

  @Query("SELECT a FROM AppUser a WHERE a.name LIKE %:q% ")
  List<AppUser> findByName(@Param("q") String q);

  @Transactional
  @Modifying
  @Query("DELETE FROM AppUser u WHERE u.id = :userId")
  void removeUser(@Param("userId") Long userId);

  @Transactional
  @Modifying
  @Query("update Student s set s.studentNumber = :studentNumber where s.id = :userId")
  void updateStudentNumber(
      @Param("userId") Long userId, @Param("studentNumber") Integer studentNumber);

  /*
  @Modifying

  @Query("update Student s set s.masters = :masters where s.id = :userId")
  void updateStudentMasters(@Param("userId") Integer userId, @Param ("masters") Masters masters);

  @Modifying

  @Query("update Student s set s.averageGrade = :averageGrade where s.id = :userId"
   )
  void updateStudentGrades(@Param("userId") Integer userId, @Param
   ("averageGrade") Float averageGrade);

  @Modifying

  @Query("update Professor p set p.emailField = :emailField where p.id = :userId"
   )
  void updateProfessorEmail(@Param("userId") Integer userId, @Param
   ("emailField") String emailField);

  @Modifying

  @Query("update Consultant c set c.company = :company where c.id = :userId")
   void updateConsultantCompany(@Param("userId") Integer userId, @Param ("company") String company); */

}

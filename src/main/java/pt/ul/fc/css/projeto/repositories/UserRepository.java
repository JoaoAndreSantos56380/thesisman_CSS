package pt.ul.fc.css.projeto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import pt.ul.fc.css.projeto.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT a FROM Master a WHERE a.name LIKE %:q% ")
    List<User> findByName(@Param("q") String q);
    
    @Transactional
    @Modifying
    @Query("INSERT INTO User (username, password, name) VALUES (:username, :password, :name)")
    void addUser(@Param("username") String username, @Param("password") String password, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :userId")
    void removeUser(@Param("userId") Integer userId);

}
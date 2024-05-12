package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Transactional
@SpringBootTest
class UserRepositoryTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;
  @Autowired private DissertationTopicRepository dissertationTopicRepository;
  @Autowired private ThesisExecutionRepository thesisExecutionRepository;
  @Autowired private ApplicationRepository ApplicationRepository;
  @Autowired private DefenseRepository defenseRepository;

  @Test
  void testUserListIsNotEmpty() {
    assertTrue(userRepository.count() > 0);
  }

  @Test
  void testFindByName() {
    Professor professor = (Professor) userRepository.findByName("cristiano ronaldo").get(0);
    assertEquals("cristiano ronaldo", professor.getName());
  }

  @Test
  void testFindByUserName() {
    Professor professor = (Professor) userRepository.findByUserName("cr7").get(0);
    assertEquals("cr7", professor.getUsername());
  }

  @Test
  void testFindByStudentNumber() {
    Student marie = (Student) userRepository.findByStudentNumber(1903).get(0);
    assertEquals(1903, marie.getStudentNumber());
  }

  @Test
  void testFindByStudentNumber2() {
    assertEquals(0, userRepository.findByStudentNumber(1911).size());
  }

  @Test
  void testFindByAvgGrade() {
	Student marie = (Student) userRepository.findByAvgGrade(19.99).get(0);
	assertEquals(19.99, marie.getAverageGrade());
  }



}

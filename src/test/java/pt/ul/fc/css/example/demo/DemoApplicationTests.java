package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootTest
class DemoApplicationTests {

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
  void testMastersListIsNotEmpty() {
    assertTrue(mastersRepository.count() > 0);
  }

  @Test
  void testTopicListIsNotEmpty() {
	assertTrue(dissertationTopicRepository.count() > 0);
  }

  @Test
  void tesisListIsNotEmpty() {
	assertTrue(thesisExecutionRepository.count() > 0);
  }

  @Test
  void ApplicationListIsNotEmpty() {
	assertTrue(ApplicationRepository.count() > 0);
  }

  @Test
  void DefenseListIsNotEmpty() {
	assertTrue(defenseRepository.count() > 0);
  }

  @Test
  void testFindByName() {
    Professor professor = (Professor) userRepository.findByName("cristiano ronaldo").get(0);
    assertEquals(professor.getName(), "cristiano ronaldo");
  }

  @Test
  void testFindByUserName() {
    Professor professor = (Professor) userRepository.findByUserName("cr7").get(0);
    assertEquals(professor.getUserName(), "cr7");
  }

  @Test
  void testFindByStudentNumber() {
    Student marie = (Student) userRepository.findByStudentNumber(1903).get(0);
    assertEquals(marie.getStudentNumber(), 1903);
  }

  @Test
  void testFindByStudentNumber2() {
    assertEquals(userRepository.findByStudentNumber(1911).size(), 0);
  }

  @Test
  void testFindByAvgGrade() {
	Student marie = (Student) userRepository.findByAvgGrade(19.99).get(0);
	assertEquals(marie.getAverageGrade(), 19.99);
  }

  @Test
  void testFindByMasterId() {
	Masters master = mastersRepository.findByName("the best").get(0);
	Student marie = (Student) userRepository.findByMasterId(master.getId()).get(0);
	assertEquals(marie.getAverageGrade(), 19.99);
	assertEquals(marie.getName(), "Marie Curie");
  }

  @Test
  void testFindByCompany() {
	Consultant jorge = (Consultant) userRepository.findByCompany("sumol").get(0);
	assertEquals(jorge.getName(), "jorge mendes");
  }
}

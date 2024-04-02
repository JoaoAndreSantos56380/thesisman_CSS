package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pt.ul.fc.css.example.demo.entities.AppUser;
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
    AppUser professor = userRepository.findByName("cristiano ronaldo").get(0);
    assertEquals(professor.getName(), "cristiano ronaldo");
  }

  void testFindByUserName() {
    AppUser professor = userRepository.findByUserName("cr7").get(0);
    assertEquals(professor.getName(), "cr7");
  }

  void testFindByStudentNumber() {
    AppUser professor = userRepository.findByUserName("cr7").get(0);
    assertEquals(professor.getName(), "cr7");
  }
}

package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;

import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
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

  /*
   * @Test
   * void testFindByName() {
   * AppUser professor = userRepository.findByName("cristiano ronaldo").get(0);
   * assertEquals(professor.getName(), "cristiano ronaldo");
   * }
   */

  @Test
  void findMastersByName() {
    assertTrue(mastersRepository.findByName("mustang").size() > 0);
  }

  @Test
  void findMastersCoordinator() {
    Professor testCoordinator = new Professor("username", "password", "test coordinator");
    userRepository.save(testCoordinator);

    Masters testMaster = new Masters("Test Masters", testCoordinator);
    mastersRepository.save(testMaster);

    System.out.println(testMaster);

    Masters recovered = mastersRepository.findByCoordinator(testCoordinator).get(0);

    assertEquals(recovered, testMaster);
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
  void testFindByMaster() {
	Masters master = mastersRepository.findByName("the best").get(0);
	Student marie = (Student) userRepository.findByMaster(master).get(0);
	assertEquals(marie.getName(), "Marie Curie");
  }

  @Test
  void testFindByCompany() {
	Consultant jorge = (Consultant) userRepository.findByCompany("sumol").get(0);
	assertEquals(jorge.getName(), "jorge mendes");
  }

  @Test
  void testMasterFindByName() {
	Masters master = (Masters) mastersRepository.findByName("the best").get(0);
	assertEquals(master.getName(), "the best");
  }

  @Test
  void testMasterFindByCoordinator() {
	Professor cr7Professor = (Professor) userRepository.findByName("cristiano ronaldo").get(0);
	Masters master = (Masters) mastersRepository.findByCoordinator(cr7Professor).get(0);
	assertEquals(master.getName(), "the best");
  }

  @Test
  void findDissertationTopicByTitle() {
    Professor testCoordinator = new Professor("username", "password", "test coordinator");
    userRepository.save(testCoordinator);

    Masters testMaster = new Masters("Test Masters", testCoordinator);
    mastersRepository.save(testMaster);

    assertEquals(1, dissertationTopicRepository.findByTitle("Futebol radiante").size());
  }

  @Test
  void findDissertationTopicBySubmitter() {
    Professor test = (Professor) userRepository.findByName("ricardo quaresma").get(0);

    assertEquals(1, dissertationTopicRepository.findBySubmitter(test).size());
  }
  /* 
  @Test
  void findDissertationTopicBySubmitter2() {
    Professor test = (Professor) userRepository.findByName("presi juri").get(0);
    DissertationTopic topicTest = dissertationTopicRepository.findBySubmitter(test).get(0);
    System.out.println(topicTest.getDescription());
    assertEquals("description", topicTest.getDescription());
  }

  @Test
  void findDissertationTopicBCompatibleMasters() {
    Professor testCoordinator = new Professor("username", "password", "test coordinator");
    userRepository.save(testCoordinator);

    Masters testMaster = mastersRepository.findByName("the best").get(0);
    DissertationTopic test = dissertationTopicRepository.findByCompatibleMasters(testMaster).get(0);

    assertEquals("description", test.getDescription());
  }*/


}


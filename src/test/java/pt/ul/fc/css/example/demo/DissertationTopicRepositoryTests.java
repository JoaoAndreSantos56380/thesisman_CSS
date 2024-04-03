package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
class DissertationTopicRepositoryTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;
  @Autowired private DissertationTopicRepository dissertationTopicRepository;
  @Autowired private ThesisExecutionRepository thesisExecutionRepository;
  @Autowired private ApplicationRepository ApplicationRepository;
  @Autowired private DefenseRepository defenseRepository;


  @Test
  void testTopicListIsNotEmpty() {
    assertTrue(dissertationTopicRepository.count() > 0);
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
  
  @Test
  void findDissertationTopicBySubmitter2() {
    Professor test = new Professor("username", "password", "test coordinator");
    
    DissertationTopic topic = new DissertationTopic("Guatemala studies", "top", 0, test, null);

    userRepository.save(test);
    dissertationTopicRepository.save(topic);
    List<DissertationTopic> topicTestList = dissertationTopicRepository.findBySubmitter(test);
    DissertationTopic topicTest = topicTestList.get(0);
    assertEquals("top", topicTest.getDescription());
  }

  @Test
  void findDissertationTopicBCompatibleMasters() {
    Professor test = new Professor("username", "password", "test coordinator");
    
    Masters masters = new Masters("Estudos economicos Europeus", test);

    userRepository.save(test);
    mastersRepository.save(masters);

    List<Masters> testMaster = mastersRepository.findByName("Estudos economicos Europeus");
    
    DissertationTopic topic = new DissertationTopic("Guatemala studies", "top", 0, test, testMaster);
    dissertationTopicRepository.save(topic);

    DissertationTopic testTopic = dissertationTopicRepository.findByCompatibleMasters(testMaster.get(0)).get(0);

    assertEquals("top", testTopic.getDescription());
  }
}
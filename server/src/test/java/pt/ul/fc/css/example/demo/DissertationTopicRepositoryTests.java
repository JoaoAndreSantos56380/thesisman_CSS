package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Transactional
@SpringBootTest
class DissertationTopicRepositoryTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;
  @Autowired private DissertationTopicRepository dissertationTopicRepository;


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

    assertEquals(false, dissertationTopicRepository.findByTitle("Futebol radiante").isPresent()/* .size() */);
  }

  @Test
  void findDissertationTopicBySubmitter() {
    Professor test = (Professor) userRepository.findByName("Einstein").get(0);

    assertEquals(0, dissertationTopicRepository.findBySubmitter(test).size());
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

    Optional<Masters> testMaster = mastersRepository.findByName("Estudos economicos Europeus");
	assertEquals(true, testMaster.isPresent());
	HashSet<Masters> set = new HashSet<Masters>();
	set.add(masters);
    DissertationTopic topic = new DissertationTopic("Guatemala studies", "top", 0, test, set);
    dissertationTopicRepository.save(topic);

    DissertationTopic testTopic = dissertationTopicRepository.findByCompatibleMasters(testMaster.get()).get(0);

    assertEquals("top", testTopic.getDescription());
  }
}

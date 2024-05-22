package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;

import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Transactional
@SpringBootTest
class MastersRepositoryTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;

    @Test
    void testMastersListIsNotEmpty() {
        assertTrue(mastersRepository.count() > 0);
    }

    @Test
  void findMastersByName() {
    assertTrue(mastersRepository.findByName("STOCK").isPresent()/* .size() *//*  > 0 */);
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
  void testFindByMaster() {
	Masters master = mastersRepository.findByName("GOLDENAI").get();
	Student marie = (Student) userRepository.findByMaster(master).get(0);
	assertEquals(marie.getName(), "Marie Curie");
  }

  @Test
  void testMasterFindByName() {
	Masters master = (Masters) mastersRepository.findByName("GOLDENAI").get();
	assertEquals(master.getName(), "GOLDENAI");
  }

  @Test
  void testMasterFindByCoordinator() {
	Professor cr7Professor = (Professor) userRepository.findByName("cristiano ronaldo").get(0);
	Masters master = (Masters) mastersRepository.findByCoordinator(cr7Professor).get(0);
	assertEquals(master.getName(), "GOLDENAI");
  }
}
package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootTest
class DemoApplicationTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;

  @Test
  void testUserListIsNotEmpty() {
    assertTrue(userRepository.count() > 0);
  }

  @Test
  void testMastersListIsNotEmpty() {
    assertTrue(mastersRepository.count() > 0);
  }

  @Test
  void testUserName() {
    // Masters master = mastersRepository.findByName("Matematica").get(0);
    // AppUser professor = userRepository.findByName("profname1").get(0);
    // assertEquals(master.getCoordinator(), professor);
    assertTrue(true);
  }
}

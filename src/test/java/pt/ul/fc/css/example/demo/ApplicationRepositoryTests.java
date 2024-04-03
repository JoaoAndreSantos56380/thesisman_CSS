package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.entities.Application;
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
public class ApplicationRepositoryTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;
  @Autowired private DissertationTopicRepository dissertationTopicRepository;
  @Autowired private ThesisExecutionRepository thesisExecutionRepository;
  @Autowired private ApplicationRepository applicationRepository;
  @Autowired private DefenseRepository defenseRepository;

  @Test
  void testApplicationRepoIsNotEmpty() {
    Professor masterCoordinator =
        new Professor("ProfessorUsername", "ProfessorPW", "ProfessorName");
    userRepository.save(masterCoordinator);

    Masters masters = new Masters("TestMasters", masterCoordinator);
    mastersRepository.save(masters);

    Student student = new Student("username", "password", "name", 0, masters);
    userRepository.save(student);

    Consultant topicSubmitter =
        new Consultant("consultant@email.com", "consultantPW", "consultantName", "TestCompany");
    userRepository.save(topicSubmitter);

    ArrayList<Masters> compatibleMasters = new ArrayList<>();
    compatibleMasters.add(masters);

    DissertationTopic topic =
        new DissertationTopic(
            "DissertationTopic", "DissertationDescription", 2.0, topicSubmitter, compatibleMasters);
    dissertationTopicRepository.save(topic);

    Application application = new Application(student, topic);

    applicationRepository.deleteAll();
    ;
    applicationRepository.save(application);

    assertTrue(applicationRepository.count() == 1);
  }
}

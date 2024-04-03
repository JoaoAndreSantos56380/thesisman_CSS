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
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Transactional
@SpringBootTest
public class ApplicationRepositoryTests {

  @Autowired private UserRepository userRepository;
  @Autowired private MastersRepository mastersRepository;
  @Autowired private DissertationTopicRepository dissertationTopicRepository;
  @Autowired private ApplicationRepository applicationRepository;

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

  @Test
  void testFindByStudent() {
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
    DissertationTopic topic2 =
        new DissertationTopic(
            "DissertationTopic2",
            "DissertationDescription2",
            2.0,
            topicSubmitter,
            compatibleMasters);
    dissertationTopicRepository.save(topic);
    dissertationTopicRepository.save(topic2);

    Application application = new Application(student, topic);
    Application application2 = new Application(student, topic2);

    applicationRepository.deleteAll();

    applicationRepository.save(application);
    applicationRepository.save(application2);

    ArrayList<Application> retrievedApplications =
        (ArrayList<Application>) applicationRepository.findByStudent(student);
    assertTrue(retrievedApplications.size() == 2);
    assertTrue(retrievedApplications.contains(application));
    assertTrue(retrievedApplications.contains(application2));
  }

  @Test
  void testFindByTopic() {
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
    DissertationTopic topic2 =
        new DissertationTopic(
            "DissertationTopic2",
            "DissertationDescription2",
            2.0,
            topicSubmitter,
            compatibleMasters);
    dissertationTopicRepository.save(topic);
    dissertationTopicRepository.save(topic2);

    Application application = new Application(student, topic);
    Application application2 = new Application(student, topic2);

    applicationRepository.deleteAll();

    applicationRepository.save(application);
    applicationRepository.save(application2);

    assertTrue(applicationRepository.findByTopic(topic).contains(application));
    assertTrue(applicationRepository.findByTopic(topic2).contains(application2));
  }
}

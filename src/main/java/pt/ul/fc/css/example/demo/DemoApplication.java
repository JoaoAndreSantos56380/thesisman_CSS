package pt.ul.fc.css.example.demo;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(
      UserRepository userRepository, MastersRepository mastersRepository, DissertationTopicRepository dissertationTopicRepository) {
    return (args) -> {
      // criar professor
      Professor prof1 = new Professor("prof1", "passprof1", "profname1");
      Professor prof2 = new Professor("prof2", "passprof2", "profname2");
      // criar mestrado com esse prof a coordenador
      Masters master1 = new Masters("master1", prof1);
      Masters master2 = new Masters("master2", prof2);

      Student student1 = new Student("student1", "pass1", "studentname1", 56380, 12.78, master1);

	  ArrayList<Masters> mastersArr = new ArrayList<Masters>();
	  mastersArr.add(master1);

	  DissertationTopic dissertationTopic = new DissertationTopic("title1","description1", 1, prof2, null, mastersArr);

      userRepository.save(prof1);
      userRepository.save(prof2);
      mastersRepository.save(master1);
      userRepository.save(student1);
      mastersRepository.save(master2);

	  dissertationTopicRepository.save(dissertationTopic);
    };
  }
}

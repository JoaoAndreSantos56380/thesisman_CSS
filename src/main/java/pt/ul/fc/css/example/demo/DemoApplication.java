package pt.ul.fc.css.example.demo;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.FinalDefense;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
//import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
//import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
//import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;

@SpringBootApplication
public class DemoApplication {

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(
      UserRepository userRepository, MastersRepository mastersRepository, DissertationTopicRepository dissertationTopicRepository, ThesisExecutionRepository thesisExecutionRepository, DefenseRepository defenseRepository, ApplicationRepository applicationRepository) {
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

	  ThesisExecution thesis = new ThesisExecution(student1, dissertationTopic, new Date(), 13);

	  thesisExecutionRepository.save(thesis);

	  ThesisDefense defense = new ThesisDefense(thesis,"1.1.1", new Date(), 14.5);

	  FinalDefense finalDefense = new FinalDefense(thesis, "1.1.1", new Date(), 16, prof1);

	  defenseRepository.save(defense);

	  defenseRepository.save(finalDefense);

	  Application application = new Application(student1, dissertationTopic);

	  applicationRepository.save(application);

      /* Masters master = new Masters("Matematica", prof1);
	    Masters master3 = new Masters("Ingles", prof1);


	  ArrayList<Masters> mastersList = new ArrayList<>();
	  mastersList.add(master);
	  mastersList.add(master2);

	  // cria um DissertationTopic
	  DissertationTopic topic = new DissertationTopic("Biologia", "Memo bom", 1231.446, prof1, null, null);
    DissertationTopic topic2 = new DissertationTopic("TREX", "Memo bom", 1231.446, prof1, null, null);
    DissertationTopic topic3 = new DissertationTopic("TREX", "Memo bom", 1231.446, prof1, null, null);

    userRepository.save(prof1);
    mastersRepository.save(master);
	  dissertationTopicRepository.save(topic);
    dissertationTopicRepository.save(topic2);
    dissertationTopicRepository.save(topic3); */
    };
  }
}

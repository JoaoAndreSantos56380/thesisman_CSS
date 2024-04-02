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
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
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
      UserRepository userRepository, MastersRepository mastersRepository, DissertationTopicRepository dissertationTopicRepository) {
    return (args) -> {
      // criar professor
      Professor prof1 = new Professor("prof1", "passProf1", "profname1");
      // criar mestrado com esse prof a coordenador
      Masters master = new Masters("Matematica", prof1);
	    Masters master2 = new Masters("Ingles", prof1);


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
    dissertationTopicRepository.save(topic3);
    };
  }
}

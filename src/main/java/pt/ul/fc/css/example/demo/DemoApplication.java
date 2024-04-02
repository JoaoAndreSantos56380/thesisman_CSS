package pt.ul.fc.css.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
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
      UserRepository userRepository, MastersRepository mastersRepository) {
    return (args) -> {
      // criar professor
      Professor prof1 = new Professor("prof1", "passProf1", "profname1");
      // criar mestrado com esse prof a coordenador
      Masters master = new Masters("Matematica", prof1);

      userRepository.save(prof1);
      mastersRepository.save(master);
    };
  }
}

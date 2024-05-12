package pt.ul.fc.css.example.demo;

import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.FinalDefense;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

	// private static final Logger log =
	// LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			UserRepository userRepository,
			MastersRepository mastersRepository,
			DissertationTopicRepository dissertationTopicRepository,
			ThesisExecutionRepository thesisExecutionRepository,
			DefenseRepository defenseRepository,
			ApplicationRepository applicationRepository) {
		return (args) -> {
			// criar professor
			Professor cr7Professor = new Professor("cr7", "password", "cristiano ronaldo");
			Professor rq7Professor = new Professor("rq7", "password", "ricardo quaresma");
			Professor presiJuri = new Professor("pjuri", "password", "presi juri");
			Consultant consultant = new Consultant("consultant@mail.com", "password", "jorge mendes", " sumol");
			Consultant consultant2 = new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol");

			// criar mestrado com esse prof a coordenador
			Masters theBestMaster = new Masters("the best", cr7Professor);
			Masters master2 = new Masters("mustang", rq7Professor);

			Student mCurieStudent = new Student("radiante", "password", "Marie Curie", 1903, 19.99, theBestMaster);

			HashSet<Masters> mastersArr = new HashSet<Masters>();
			mastersArr.add(theBestMaster);

			DissertationTopic dissertationTopicFutebolRadiante = new DissertationTopic("Futebol radiante",
					"description", 99999, rq7Professor, mastersArr);

			userRepository.save(consultant);
			userRepository.save(consultant2);
			userRepository.save(presiJuri);
			userRepository.save(cr7Professor);
			userRepository.save(rq7Professor);
			mastersRepository.save(theBestMaster);
			userRepository.save(mCurieStudent);
			mastersRepository.save(master2);

			dissertationTopicRepository.save(dissertationTopicFutebolRadiante);

			ThesisExecution thesis = new ThesisExecution(mCurieStudent, dissertationTopicFutebolRadiante, "2024/2025");

			thesisExecutionRepository.save(thesis);

			ThesisDefense defense = new ThesisDefense(thesis, "1.1.1", new Date());

			FinalDefense finalDefense = new FinalDefense(thesis, "1.1.1", new Date(), presiJuri);

			defenseRepository.save(defense);

			defenseRepository.save(finalDefense);

			Application application = new Application(mCurieStudent, dissertationTopicFutebolRadiante);

			applicationRepository.save(application);

		};
	}

}

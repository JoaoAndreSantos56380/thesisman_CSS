package pt.ul.fc.css.example.demo;

import java.util.Date;
import java.util.HashSet;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
/* import pt.ul.fc.css.example.demo.services.UserService; */

@SpringBootApplication
public class DemoApplication {

	/* @Autowired
	private PasswordEncoder passwordEncoder; */

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
			ApplicationRepository applicationRepository,
			PasswordEncoder passwordEncoder) {
		return (args) -> {
			// criar professor
			Professor cr7Professor = new Professor("ronaldo", "password", "cristiano ronaldo");
			Professor rq7Professor = new Professor("quaresma", "password", "ricardo quaresma");
			Professor presiJuri = new Professor("pjuri", "password", "presi juri");
			Consultant consultant = new Consultant("consultant@mail.com", "password", "jorge mendes", " sumol");
			Consultant consultant2 = new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol");

			// criar mestrado com esse prof a coordenador
			Masters theBestMaster = new Masters("GOLDENAI", cr7Professor);
			Masters master2 = new Masters("INFORMATICA", rq7Professor);
			Masters master3 = new Masters("STOCK", presiJuri);

			Student mCurieStudent = new Student("radiante", "password", "Marie Curie", 1903, 19.99, theBestMaster);

			HashSet<Masters> mastersArr = new HashSet<Masters>();
			mastersArr.add(theBestMaster);

			DissertationTopic dissertationTopicFutebolRadiante = new DissertationTopic("Futebol radiante",
					"description", 99999, rq7Professor, mastersArr);

			String encodedPassword = passwordEncoder.encode(consultant.getPassword());
			consultant.setPassword(encodedPassword);
			userRepository.save(consultant);

			//userService.registerNewUser(consultant2);
			encodedPassword = passwordEncoder.encode(consultant.getPassword());
			consultant2.setPassword(encodedPassword);
			userRepository.save(consultant2);


			//userService.registerNewUser(presiJuri);
			encodedPassword = passwordEncoder.encode(presiJuri.getPassword());
			presiJuri.setPassword(encodedPassword);
			userRepository.save(presiJuri);

			//userService.registerNewUser(cr7Professor);
			encodedPassword = passwordEncoder.encode(cr7Professor.getPassword());
			cr7Professor.setPassword(encodedPassword);
			userRepository.save(cr7Professor);

			//userService.registerNewUser(rq7Professor);
			encodedPassword = passwordEncoder.encode(rq7Professor.getPassword());
			rq7Professor.setPassword(encodedPassword);
			userRepository.save(rq7Professor);

			/* userRepository.save(consultant);
			userRepository.save(consultant2);
			userRepository.save(presiJuri);
			userRepository.save(cr7Professor);
			userRepository.save(rq7Professor); */
			mastersRepository.save(theBestMaster);
			/* userRepository.save(mCurieStudent); */
			//userService.registerNewUser(mCurieStudent);
			encodedPassword = passwordEncoder.encode(mCurieStudent.getPassword());
			mCurieStudent.setPassword(encodedPassword);
			userRepository.save(mCurieStudent);
			mastersRepository.save(master2);
			mastersRepository.save(master3);

			dissertationTopicRepository.save(dissertationTopicFutebolRadiante);

			ThesisExecution thesis = new ThesisExecution(mCurieStudent, dissertationTopicFutebolRadiante, "2024/2025");

			thesisExecutionRepository.save(thesis);

			ThesisDefense defense = new ThesisDefense(thesis, "LOCATION", new Date());

            defense.setGrade(17);

            FinalDefense finalDefense = new FinalDefense(thesis, "FINAL_LOCATION", new Date(), presiJuri);

            finalDefense.setGrade(18);
			defenseRepository.save(defense);

			defenseRepository.save(finalDefense);

			Application application = new Application(mCurieStudent, dissertationTopicFutebolRadiante);

			applicationRepository.save(application);

		};
	}

}

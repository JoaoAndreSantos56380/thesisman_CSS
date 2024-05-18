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

			Professor prof1 = new Professor("prof1", "password", "prof1");
			Professor prof2 = new Professor("prof2", "password", "prof2");
			Professor prof3 = new Professor("prof3", "password", "prof3");
			Professor prof4 = new Professor("prof4", "password", "prof4");
			Professor prof5 = new Professor("prof5", "password", "prof5");

			userRepository.save(prof1);
			userRepository.save(prof2);
			userRepository.save(prof3);
			userRepository.save(prof4);
			userRepository.save(prof5);

			// criar mestrado com esse prof a coordenador
			Masters theBestMaster = new Masters("GOLDENAI", cr7Professor);
			Masters master2 = new Masters("INFORMATICA", rq7Professor);
			Masters master3 = new Masters("STOCK", presiJuri);

			Masters master10 = new Masters("master10", prof1);
			Masters master9 = new Masters("master9", prof2);
			Masters master8 = new Masters("master8", prof3);
			Masters master7 = new Masters("master7", prof4);

			mastersRepository.save(master10);
			mastersRepository.save(master9);
			mastersRepository.save(master8);
			mastersRepository.save(master7);

			Student mCurieStudent = new Student("radiante", "password", "Marie Curie", 1903, 19.99, theBestMaster);

			Student student1 = new Student("student1", "password", "student1", 1903, 19.99, master10);
			Student student2 = new Student("student2", "password", "student2", 1903, 19.99, master9);
			Student student3 = new Student("student3", "password", "student3", 1903, 19.99, master8);
			Student student4 = new Student("student4", "password", "student4", 1903, 19.99, master7);
			Student student5 = new Student("student5", "password", "student5", 1903, 19.99, master7);

			userRepository.save(student1);
			userRepository.save(student2);
			userRepository.save(student3);
			userRepository.save(student4);
			userRepository.save(student5);

			HashSet<Masters> mastersArr = new HashSet<Masters>();
			mastersArr.add(theBestMaster);
			HashSet<Masters> mastersArr2 = new HashSet<Masters>();
			mastersArr.add(master10);
			mastersArr.add(master9);
			mastersArr.add(master8);
			mastersArr.add(master7);

			DissertationTopic dissertationTopicFutebolRadiante = new DissertationTopic("Futebol radiante", "description", 99999, rq7Professor, mastersArr);
			DissertationTopic dissertationTopic1 = new DissertationTopic("titulo1", "description", 99999, prof1, mastersArr2);
			DissertationTopic dissertationTopic2 = new DissertationTopic("titulo2", "description", 99999, prof2, mastersArr2);
			DissertationTopic dissertationTopic3 = new DissertationTopic("titulo3", "description", 99999, prof3, mastersArr2);

			dissertationTopicRepository.save(dissertationTopic1);
			dissertationTopicRepository.save(dissertationTopic2);
			dissertationTopicRepository.save(dissertationTopic3);

			Application application1 = new Application(student1, dissertationTopic1);
			Application application2 = new Application(student2, dissertationTopic2);
			Application application3 = new Application(student3, dissertationTopic3);

			applicationRepository.save(application1);
			applicationRepository.save(application2);
			applicationRepository.save(application3);

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

			mastersRepository.save(theBestMaster);

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
